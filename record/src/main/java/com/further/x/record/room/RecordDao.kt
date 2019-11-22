package com.further.x.record.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by Zion
 * 2019/11/22.
 */
@Dao
interface RecordDao {
    @Query("SELECT * FROM record ORDER BY id")
    fun getRecords():   RecordData


    @Insert
    fun insert(recordData: RecordData)
}