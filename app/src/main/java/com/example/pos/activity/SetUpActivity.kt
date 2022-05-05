package com.example.pos.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pos.R

class SetUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_up)

        setupButton()
    }

    private fun setupButton() {

        val btnSetUp = findViewById(R.id.btn_setup) as Button

        btnSetUp.setOnClickListener {

            val txtBranchID = findViewById(R.id.editTextTextPersonBranchID) as TextView
            val txtStaffID = findViewById(R.id.editTextTextPersonStaffID) as TextView

            val preferences = getSharedPreferences("setup", Context.MODE_PRIVATE)

            val edit = preferences.edit()
            edit.putString("branchID", txtBranchID.text.toString())
            edit.putString("staffID", txtStaffID.text.toString())
            edit.commit()

            Toast.makeText(this, "Setup Successfully", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, CommandActivity::class.java).apply {
            }

            //Start the activity class that is in the intent variable
            startActivity(intent)
        }
    }
}