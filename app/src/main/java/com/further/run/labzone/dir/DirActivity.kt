package com.further.run.labzone.dir

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.further.run.R
import com.further.run.labzone.recyclerview.DateVo
import com.further.run.util.ProjectUtil
import com.further.run.util.RVAdapter
import com.further.run.util.RVHolder
import java.util.*

/**
 * Created by Zion
 * 2019/4/2.
 */
class DirActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dir)
        findViewById<TextView>(R.id.dir_name).text = externalCacheDir.absolutePath;
        val mMainRV = findViewById<RecyclerView>(R.id.recycler_view)
        mMainRV.layoutManager = LinearLayoutManager(this)
        mMainRV.adapter = object : RVAdapter<Class<*>>(this, ProjectUtil.getClasses(), R.layout.child_item_double_activity) {
            override fun getLayoutResId(data: Class<*>): Int {
                return if (!data.name.contains("Design")) R.layout.child_item_double_activity else R.layout.child_item_double_activity
            }

            override fun convert(holder: RVHolder<*>, data: Class<*>, positon: Int) {
                holder.setText(R.id.content, data.name)

            }
        }

    }
}