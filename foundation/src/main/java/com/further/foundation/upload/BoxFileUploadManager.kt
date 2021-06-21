package com.further.foundation.upload

import android.util.Log
import androidx.annotation.NonNull
import java.io.*

/**
 * create by zion.hu on 4:02 PM, Wed, 4/28/21
 *
 * 描述：-- 盒子 wifi 上传文件
 **/
object BoxFileUploadManager {
    private const val  SINGLE_SIZE = 20 * 1000 * 1000L
    fun upload(fileName: String?, @NonNull uploadCallback: UploadCallback) {

        val file = File(fileName)

        // 1. 检查文件是否存在
        if (!file.exists() || !file.isFile) {
            uploadCallback.onFail("文件不存在")
            return
        }
        val md5 = UploadUtil.getMd5(file)
        var totalSize = 0L
        UploadUtil.init(object : UploadCallback {
            override fun onSuccess() {

            }

            override fun onFail(error: String) {

            }

            override fun onProgress(speed: Long, currentSize: Long) {

                totalSize += currentSize
                uploadCallback.onProgress(speed, totalSize)

            }

        })
        // 2.检查文件大小，如果超过 20M，则需要分成 N 个 20M 以下大小的包，后缀加.1 .2 传输
        if (file.length() > SINGLE_SIZE) {

            val count = getCount(file.length())
            val outputStreamList = ArrayList<FileOutputStream>()
            val fileList = ArrayList<File>()
            for (i in 0 until count) {
                var tempFile = File(file.parent, fileName.plus(".${i + 1}"))
                if (!tempFile.exists()) tempFile.createNewFile()
                outputStreamList.add(FileOutputStream(tempFile))
                fileList.add(tempFile)
            }
            saveFile(file, outputStreamList)

            fileList.forEach {
                Log.d("UploadUtil", "UploadUtil  file  ${fileList.size} child length : ${it.length()}")
            }
            transferUpload(0, fileList, md5, uploadCallback)
        } else
        // 传输文件
            UploadUtil.upload(file, md5, uploadCallback)
    }

    private fun getCount(length : Long) : Int {
        var count = 0
        var currentLength = length
        while(currentLength > 0) {
            currentLength -= SINGLE_SIZE
            count ++
        }
        return  count
    }

    fun transferUpload(
        index: Int,
        array: ArrayList<File>,
        md5: String,
        uploadCallback: UploadCallback
    ) {
        if (index < array.size) {
            UploadUtil.upload(
                array[index],
                if (index == array.size - 1) md5 else "",
                object : UploadCallback {
                    override fun onSuccess() {
                        if (index == array.size - 1) {
                            uploadCallback.onSuccess()
                        } else {
                            transferUpload(index + 1, array, md5, uploadCallback)
                        }
                    }

                    override fun onFail(error: String) {
                        uploadCallback.onFail(error)
                    }

                    override fun onProgress(speed: Long, currentSize: Long) {
                        var actualSize = currentSize
                        for (i in 0 until index) {
                            actualSize += array[i].length()
                        }

                        Log.d(
                            "UploadUtil",
                            "UploadUtil $index byteCount : $speed currentSize : $currentSize"
                        )
                        Log.d(
                            "UploadUtil",
                            "UploadUtil $index byteCount : $speed actualSize : $actualSize"
                        )
                        uploadCallback.onProgress(speed, actualSize)
                    }

                })
        }


    }

    private fun saveFile(outFile: File, outputStreamList: ArrayList<FileOutputStream>) {

        val buf = ByteArray(2048)
        var len: Int

        var srcInputStream: InputStream = outFile.inputStream()

        try {
            var lenSize = 0
            var index = 0
            while (srcInputStream.read(buf).also { len = it } != -1) {
                lenSize += len
                outputStreamList[index].write(buf, 0, len)
                if (lenSize + 2048 > SINGLE_SIZE) {
                    Log.d("UploadUtil", "UploadUtil : $lenSize")
                    index++
                    lenSize = 0
                }

            }
            outputStreamList.forEach {
                it.flush()
            }

        } catch (e: FileNotFoundException) {

        } catch (e: Exception) {


        } finally {
            try {
                srcInputStream.close()
                outputStreamList.forEach {
                    it.close()
                }
            } catch (e: Exception) {

            }
        }
    }
}