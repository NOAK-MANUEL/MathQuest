package com.buzigrowth.mathquest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProgressDao {

    @Insert
    suspend fun insert(progress: ProgressEntity)

    @Query("SELECT * FROM progress ORDER BY level")
    suspend fun getAllProgress(): List<ProgressEntity>

    @Query("SELECT * FROM progress WHERE level = :level")
     fun getByLevel(level: Int): ProgressEntity?

    @Update
    suspend fun updateProgress(progress: ProgressEntity)
}