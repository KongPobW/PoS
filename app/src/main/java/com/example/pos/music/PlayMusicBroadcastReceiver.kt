package com.example.pos.music

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.pos.ACTION_POS_PLAY
import com.example.pos.ACTION_POS_STOP

class PlayMusicBroadcastReceiver : BroadcastReceiver() {

    private val TAG = "PlayMusicBroadcastReceiver"

    @SuppressLint("LongLogTag")
    override fun onReceive(context: Context, intent: Intent) {

        StringBuilder().apply {

            append("Action: ${intent.action}\n")
            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")

            toString().also { log ->
                Log.d(TAG, log)
                Toast.makeText(context, log, Toast.LENGTH_SHORT).show()
            }

            if (intent.action == ACTION_POS_PLAY) {
                Intent(context, PlayMusicInBGService::class.java).also { intent ->
                    context.startService(intent)
                }
            }

            if (intent.action == ACTION_POS_STOP) {
                Intent(context, PlayMusicInBGService::class.java).also { intent ->
                    context.stopService(intent)
                }
            }
        }
    }
}