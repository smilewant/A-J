package com.further.x.city

import android.content.Context
import android.content.res.AssetManager
import com.further.x.room.AppDatabase
import com.further.x.room.CityData
import java.io.IOException
import java.util.*

/**
 * Created by Zion
 * 2019/12/12.
 */
object CityCatch {
    fun catch1(context: Context) {
        val db = AppDatabase.getInstance(context)

        var asset: AssetManager = context.resources.assets
        try {
            var scan = Scanner(asset.open("city.txt"), "utf-8")
            var line: String? = null
            var i = 0
            while (scan.hasNextLine()) {
                line = scan.nextLine()
                if (!line.trim().isEmpty()) {
                    var arr = line.trim().split(" ")
                    var city = CityData()
                    city.city = arr[1]
                    city.citykey = arr[0]
                    when {
                        city.citykey == "1" -> {
                            city.parent = "0"
                            city.citykey = i.toString()
                            i++
                        }
                        city.citykey.endsWith("01") && !city.citykey.equals("101021101") -> {

                            city.parent = "1"
                        }
                        city.city.endsWith("市")
                                && !city.city.equals("新竹市")
                                && !city.city.equals("海拉尔")
                                && !city.city.equals("三原")
                                && !city.city.equals("延长")
                                && !city.city.equals("赫山区 ")
                                && !city.city.equals("台北市") -> city.parent = "1"
                        else -> city.parent = "2"
                    }
                    db.getCity().insert(city)
                }
            }
        } catch (e: IOException){

        }


    }

    fun get(context: Context): List<CityData> {

        return AppDatabase.getInstance(context)
                .getCity().getCitys()
    }
}