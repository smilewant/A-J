package com.further.x.record

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.further.foundation.BaseActivity
import com.further.x.R
import com.further.x.room.RecordData
import com.further.x.record.vo.RecordItemVo
import com.further.x.room.AppDatabase
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

        val db =  AppDatabase.getInstance(this)
        thread {
            db.recordDao()?.insert(RecordData("12345", "", "", "content"))
        }

        var items = ArrayList<RecordItemVo>()


        for (i in 0..4) {
            items.apply {  db.recordDao()?.getRecords()}
        }
        var recordRecyclerView : RecyclerView = findViewById(R.id.record_recycler)
        recordRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//         adapter = DataBindingRecyclerViewAdapter(this, R.layout.record_item, BR.item, items)
//        recordRecyclerView.adapter = adapter

        adapter = RecordAdapter(this, items)
        recordRecyclerView.adapter = adapter

        findViewById<ImageView>(R.id.add).setOnClickListener {
            thread {
                items.apply {  db.recordDao()?.getRecords()}
//            adapter.mItems = items

            }
            Thread.sleep(100)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
//            AsyncTask.execute {  }

        }
    }

}