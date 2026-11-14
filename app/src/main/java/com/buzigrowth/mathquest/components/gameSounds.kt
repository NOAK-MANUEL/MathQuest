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
    private val beep = pool.load(context, R.raw.tick,1)

//    private val music = MediaPlayer.create(context,R.raw.bg_music).apply {
//        isLooping=true
//        setVolume(0.35f,0.35f)
//     }
//    private val stageMusic = MediaPlayer.create(context,R.raw.sonican).apply {
//        isLooping=true
//        setVolume(0.35f,0.35f)
//     }

    fun playCorrect() = pool.play(correct,1f,1f,1,0,1f)
    fun playWrong() = pool.play(wrong,1f,1f,1,0,1f)
    fun playTap() = pool.play(tap,1f,1f,1,0,1f)
    fun playComplete() = pool.play(complete,1f,1f,1,0,1f)
    fun playBeep() = pool.play(beep,1f,1f,1,0,1f)


    private val appContext = context.applicationContext
    private var intro: MediaPlayer? = null
    private var stage: MediaPlayer? = null

    private fun newPlayer(res: Int): MediaPlayer? =
        MediaPlayer.create(appContext, res)?.apply {
            isLooping = true
            setVolume(0.35f, 0.35f)
        }

    fun startIntroMusic() {
        if (stage != null) return
        if (intro == null) intro = newPlayer(R.raw.bg_music)
        intro?.start()
    }

    fun pauseIntroMusic() { intro?.pause() }

    fun startStageMusic() {
        pauseIntroMusic()
        stage?.release()
        stage = newPlayer(R.raw.sonican)
        stage?.start()
    }

    fun pauseStageMusic() {
        stage?.release()
        stage = null
    }

    fun release() {
        pool.release()
        intro?.release(); intro = null
        stage?.release(); stage = null
    }
    fun releasePool(){pool.release()}
}