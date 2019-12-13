package com.further.x.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by Zion
 * 2019/12/12.
 */
@Dao
interface CityDao {
    @Query("SELECT * FROM city ")
    fun getCitys(): List<CityData>


    @Insert
    fun insert(city: CityData)
}