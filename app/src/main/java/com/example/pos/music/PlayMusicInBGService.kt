package com.example.pos.music

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import android.widget.Toast
import com.example.pos.R

class PlayMusicInBGService : Service() {

    private var playStatus: Boolean = false
    private var mPlayer: MediaPlayer? = null
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {

            try {
                Toast.makeText(this@PlayMusicInBGService, "Service Created", Toast.LENGTH_SHORT).show()
                if (!playStatus) {
                    mPlayer = MediaPlayer.create(this@PlayMusicInBGService, R.raw.sample)
                    mPlayer?.start()
                    playStatus = true
                }
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
    }

    override fun onCreate() {
        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service Starting", Toast.LENGTH_SHORT).show()
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            serviceHandler?.sendMessage(msg)
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        mPlayer?.stop()
        playStatus = false
        stopSelf()
        Toast.makeText(this, "Service Done", Toast.LENGTH_SHORT).show()
    }
}