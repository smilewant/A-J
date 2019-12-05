package com.further.x.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Zion
 * 2019/11/22.
 */
@Database(entities = [RecordData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun RecordDao(): RecordDao

}