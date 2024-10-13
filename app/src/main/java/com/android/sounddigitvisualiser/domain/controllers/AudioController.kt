package com.android.sounddigitvisualiser.domain.controllers

import android.content.Context
import android.media.MediaRecorder
import android.util.Log

class AudioController (private val context: Context){

    private var mediaRecorder: MediaRecorder? = null

    fun start(){
        Log.d(TAG,"onStart")

        stop()

        mediaRecorder = MediaRecorder()

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(getAudioPath())
        mediaRecorder?.prepare()
        mediaRecorder?.start()

    }

    private fun getAudioPath() : String {
        return "${context.externalCacheDirs[0].absolutePath}/${System.currentTimeMillis()}.wav"
    }

    fun getVolume():Int{
        return mediaRecorder?.maxAmplitude ?: 0
    }

    fun isAudioInputing() : Boolean {
        return mediaRecorder != null
    }

    fun stop(){
        if (mediaRecorder == null){
            Log.d(TAG,"onStop")
            try {
                mediaRecorder?.pause()
                mediaRecorder?.stop()
                mediaRecorder?.release()
            } catch (ex:Exception){
            }
            mediaRecorder = null
        }
    }
    fun reset(){
        mediaRecorder?.reset()
    }

    companion object{
        const val TAG = "AudioController"
    }
}