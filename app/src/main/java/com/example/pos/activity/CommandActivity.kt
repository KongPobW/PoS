package com.example.pos.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.pos.ACTION_POS_PLAY
import com.example.pos.ACTION_POS_STOP
import com.example.pos.R
import com.example.pos.music.PlayMusicBroadcastReceiver
import com.example.pos.music.PlayMusicInBGService

class CommandActivity : AppCompatActivity() {

    private val CHANNEL_ID = "90200"
    private val notificationID = 1
    private val EXTRA_NOTIFICATION_ID = "notification_id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command)

        newProductButton()

        addProductButton()

        playMusicButton()

        stopMusicButton()
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Play Background Status Channel"
            val descriptionText = "A notification to show playing background music status"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun newProductButton() {

        val btnNewProduct = findViewById(R.id.btn_newProduct) as Button

        btnNewProduct.setOnClickListener {

            val intent = Intent(this, ProductCatActivity::class.java).apply {
            }
            intent.putExtra("BtnInCommand", "New")
            startActivity(intent)
        }
    }

    private fun addProductButton() {

        val btnAddProduct = findViewById(R.id.btn_detProduct) as Button

        btnAddProduct.setOnClickListener {

            val intent = Intent(this, ProductCatActivity::class.java).apply {
            }
            intent.putExtra("BtnInCommand", "Add")
            startActivity(intent)
        }
    }

    private fun playMusicButton() {

        val btnPlayMusic = findViewById(R.id.btn_playMusic) as Button

        btnPlayMusic.setOnClickListener {

            Intent(this, PlayMusicInBGService::class.java).also { intent ->
                startService(intent)
            }

            val intent = Intent(this, CommandActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

            val playIntent = Intent(this, PlayMusicBroadcastReceiver::class.java).apply {
                action = ACTION_POS_PLAY
                putExtra(EXTRA_NOTIFICATION_ID, notificationID)
            }

            val playPendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, playIntent, 0)

            val stopIntent = Intent(this, PlayMusicBroadcastReceiver::class.java).apply {
                action = ACTION_POS_STOP
                putExtra(EXTRA_NOTIFICATION_ID, notificationID)
            }

            val stopPendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, stopIntent, 0)

            createNotificationChannel()

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_bg_music_stat)
                .setContentTitle("Stamford POS Background Music Status")
                .setContentText("Music is playing")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .addAction(R.drawable.ic_action_play, "Play", playPendingIntent)
                .addAction(R.drawable.ic_action_stop, "Stop", stopPendingIntent)

            with(NotificationManagerCompat.from(this)) {
                notify(notificationID, builder.build())
            }
        }
    }

    private fun stopMusicButton() {

        val btnStopMusic = findViewById(R.id.btn_stopMusic) as Button

        btnStopMusic.setOnClickListener {

            Intent(this, PlayMusicInBGService::class.java).also { intent ->
                stopService(intent)
            }
        }
    }
}