package com.further.x.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Zion
 * 2019/11/22.
 */
@Database(entities = [RecordData::class, CityData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recordDao(): RecordDao
    abstract fun getCity(): CityDao


    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build().also { instance = it }
            }
        }


    }
}