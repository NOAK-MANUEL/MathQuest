package com.buzigrowth.mathquest.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProgressView(app: Application): AndroidViewModel(app) {
    private val dao = AppDatabase.getDatabase(app).progressDao()

    private val _progress = MutableStateFlow<List<ProgressEntity>>(emptyList())

    val progress: StateFlow<List<ProgressEntity>> = _progress

    init {
        load()
    }

    fun load()= viewModelScope.launch {
        _progress.value = dao.getAllProgress()
    }

    fun getLevel(level: Int): ProgressEntity? =  dao.getByLevel(level)


    fun completeLevel(level:Int, stars:Int, numberGot:Int, numberFailed:Int,timeUsage: Int,completed: Boolean) = viewModelScope.launch {
        val existing = dao.getByLevel(level)
        if (existing==null){
            dao.insert(progress = ProgressEntity(level = level, stars = stars, numberGot = numberGot, numberFailed = numberFailed, timeUsage = timeUsage, completed = completed))
        }else{
            dao.updateProgress(existing.copy(stars = stars, numberFailed = numberFailed, numberGot = numberGot, timeUsage = timeUsage, completed = completed,numberOfTimesTried = existing.numberOfTimesTried+1))
        }
        load()
    }
}