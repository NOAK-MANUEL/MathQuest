package com.buzigrowth.mathquest.components

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import com.buzigrowth.mathquest.R

class GameSounds(context: Context){
    private val pool = SoundPool.Builder()
        .setMaxStreams(4)
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        )
        .build()

    private val correct = pool.load(context,R.raw.correct,1)
    private val wrong = pool.load(context,R.raw.wrong,1)
    private val tap = pool.load(context,R.raw.tap,1)
    private val complete = pool.load(context,R.raw.level_complete,1)

    private val music = MediaPlayer.create(context,R.raw.bg_music).apply {
        isLooping=true
        setVolume(0.35f,0.35f)
     }

    fun playCorrect() = pool.play(correct,1f,1f,1,0,1f)
    fun playWrong() = pool.play(wrong,1f,1f,1,0,1f)
    fun playTap() = pool.play(tap,1f,1f,1,0,1f)
    fun playComplete() = pool.play(complete,1f,1f,1,0,1f)
    fun startMusic(){if (!music.isPlaying) music.start()}
    fun pauseMusic(){if (music.isPlaying) music.pause()}

    fun releasePool(){pool.release()}
    fun release(){pool.release(); music.release()}
}