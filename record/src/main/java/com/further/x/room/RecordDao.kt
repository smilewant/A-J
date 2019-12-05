package com.further.x.room

import androidx.room.Dao
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