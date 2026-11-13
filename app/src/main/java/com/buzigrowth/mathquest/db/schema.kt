package com.buzigrowth.mathquest.db

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Mode{
    FRESH,
    BLAZING,
    CRAZY
}

@Entity(tableName = "progress")
data class ProgressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,


    val level: Int,

    val stars: Int,

    val numberFailed: Int,

    val numberGot: Int,

    val timeUsage: Int,

    val mode: Mode ,

    val completed: Boolean,

    val numberOfTimesTried:Int=1,

)