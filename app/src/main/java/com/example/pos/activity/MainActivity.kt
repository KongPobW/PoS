package com.example.pos.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.pos.R

class MainActivity : AppCompatActivity() {

    private val longClickDuration = 1000L
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pickAccount()

        loginButton()

        appNameText()
    }

    fun pickAccount() {
        val preferences = getPreferences(Context.MODE_PRIVATE)
        val name = preferences.getString("name", "")
        val pass = preferences.getString("pass", "")

        val fieldName = findViewById(R.id.editTextTextPersonName) as TextView
        val fieldPass = findViewById(R.id.editTextTextPassword) as TextView
        val checkBox = findViewById(R.id.checkBox) as CheckBox

        fieldName.setText(name)
        fieldPass.setText(pass)

        if (preferences.getBoolean("statusCheckBoxRememberMe", false)) {
            checkBox.setChecked(true)
        } else {
            checkBox.setChecked(false)
        }
    }

    fun onLogin() {
        val checkBox = findViewById(R.id.checkBox) as CheckBox

        if (checkBox.isChecked) {
            val preferences = getPreferences(Context.MODE_PRIVATE)
            val edit = preferences.edit()
            val fieldName = findViewById(R.id.editTextTextPersonName) as TextView
            val fieldPass = findViewById(R.id.editTextTextPassword) as TextView

            edit.putString("name", fieldName.text.toString())
            edit.putString("pass", fieldPass.text.toString())
            edit.putBoolean("statusCheckBoxRememberMe", true)
            edit.commit()

            //Show this message on screen
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        }
    }

    fun View.setOnVeryLongClickListener(listener: () -> Unit) {

        setOnTouchListener(object: View.OnTouchListener {

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                if (event?.action == MotionEvent.ACTION_DOWN) {
                    handler.postDelayed({ listener.invoke() }, longClickDuration)
                } else if (event?.action == MotionEvent.ACTION_UP) {
                    handler.removeCallbacksAndMessages(null)
                }
                return true
            }
        })
    }

    fun loginButton() {

        val btnLogin = findViewById(R.id.btn_login) as Button

        btnLogin.setOnClickListener {

            onLogin()

            //Store the activity class that will be started
            val intent = Intent(this, CommandActivity::class.java).apply {
            }

            //Start the activity class that is in the intent variable
            startActivity(intent)
        }
    }

    fun appNameText() {

        val txtAppName = findViewById(R.id.txtAppName) as TextView

        txtAppName.setOnVeryLongClickListener { //Wait for 1 second

            val intent = Intent(this, SettingActivity::class.java).apply {
            }

            startActivity(intent)
        }
    }
}