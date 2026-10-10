package com.buzigrowth.mathquest.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress")
data class ProgressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,


    val level: Int,

    val stars: Int,

    val numberFailed: Int,

    val numberGot: Int,

    val timeUsage: Long,

    val numberOfTimesTried:Int=1,

)