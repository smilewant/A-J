package com.further.x.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Zion
 * 2019/11/22.
 */
@Entity(tableName = "record")
data class RecordData (
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    val date : String,
    val name :String,
    val content: String
)