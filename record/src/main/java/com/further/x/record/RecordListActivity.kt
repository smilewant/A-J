package com.further.x.record

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.further.foundation.BaseActivity
import com.further.x.R
import com.further.x.room.RecordData
import com.further.x.room.test
import com.further.x.record.vo.RecordItemVo
import kotlin.concurrent.thread


/**
 * Created by Zion
 * 2019/11/19.
 */
class RecordListActivity : BaseActivity() {
    lateinit var adapter : RecordAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)

        var t: test = test()
        thread {
            t.createDb(this)
            t.recordDao?.insert(RecordData("12345", "", "", "content"))

        }

        var items = ArrayList<RecordItemVo>()


        for (i in 0..4) {
            items.add(RecordItemVo().apply {
                var r = t.recordDao?.getRecords()
                name = ""
                date = "2019-11-22"
                content = r?.content?:""
                id = System.nanoTime().toString()
            })
        }
        var recordRecyclerView : RecyclerView = findViewById(R.id.record_recycler)
        recordRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//         adapter = DataBindingRecyclerViewAdapter(this, R.layout.record_item, BR.item, items)
//        recordRecyclerView.adapter = adapter

        adapter = RecordAdapter(this, items)
        recordRecyclerView.adapter = adapter

        findViewById<ImageView>(R.id.add).setOnClickListener {
            thread {
                items.add(RecordItemVo().apply {
                    var r = t.recordDao?.getRecords()
                    name = ""
                    date = "2019-11-21"
                    content =r?.content?:""
                    id = System.nanoTime().toString()
                })
//            adapter.mItems = items

            }
            Thread.sleep(100)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
//            AsyncTask.execute {  }

        }
    }

}