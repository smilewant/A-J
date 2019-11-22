package com.further.x.record.room

import android.content.Context
import androidx.room.Room

/**
 * Created by Zion
 * 2019/11/22.
 */
class test {
     var recordDao: RecordDao? = null
    lateinit var db: AppDatabase


    fun createDb(context:Context){
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        recordDao= db.RecordDao()
    }


}