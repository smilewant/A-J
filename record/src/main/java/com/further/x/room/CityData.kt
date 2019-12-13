package com.further.x.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Zion
 * 2019/12/4.
 */
@Entity(tableName = "city")
class CityData {
    var city: String = ""
    @PrimaryKey
    @ColumnInfo(name = "city_key")var citykey: String = ""
    var parent: String = ""

}