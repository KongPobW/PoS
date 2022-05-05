package com.example.pos.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.R
import com.example.pos.contact.Contact
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.provider.ContactsContract
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pos.adapter_contact.ContactAdapter
import java.util.*
import kotlin.collections.ArrayList

class ContactActivity : AppCompatActivity() {

    var rvContact: RecyclerView? = null
    val conList: ArrayList<Contact> = ArrayList<Contact>()
    val srhList: ArrayList<Contact> = ArrayList<Contact>()
    var adapter: ContactAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        checkPermission()
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this@ContactActivity, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@ContactActivity, arrayOf(Manifest.permission.READ_CONTACTS), 100)
        } else {
            prepare()
        }
    }

    private fun prepare() {
        cursor()

        rvContact = findViewById(R.id.rvContact)
        rvContact!!.layoutManager = LinearLayoutManager(this)

        adapter = ContactAdapter(srhList)
        rvContact!!.adapter = adapter

        search()
    }

    @SuppressLint("Range")
    private fun cursor() {
        val uri = ContactsContract.Contacts.CONTENT_URI
        val sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        val cursor = contentResolver.query(uri, null, null, null, sort)

        if (cursor!!.count > 0) {

            while (cursor.moveToNext()) {
                val contactID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                val selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?"
                val cursorPhone = contentResolver.query(uriPhone, null, selection, arrayOf(contactID), null)

                if (cursorPhone!!.moveToNext()) {
                    val contactPhone = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val contact = Contact()

                    contact.setName(contactName)
                    contact.setPhone(contactPhone)

                    conList.add(contact)
                    srhList.add(contact)

                    cursorPhone.close()
                }
            }
            cursor.close()
        }
    }

    private fun search() {
        val search = findViewById(R.id.searchView) as SearchView

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                srhList.clear()

                val searchText = newText!!.toLowerCase(Locale.getDefault())

                if (searchText.isNotEmpty()) {

                    conList.forEach {

                        if (it.getName()?.toLowerCase(Locale.getDefault())?.contains(searchText) == true || it.getPhone()?.toLowerCase(Locale.getDefault())?.contains(searchText) == true) {
                            srhList.add(it)
                        }
                    }
                    rvContact!!.adapter?.notifyDataSetChanged()
                } else {
                    srhList.clear()
                    srhList.addAll(conList)

                    rvContact!!.adapter?.notifyDataSetChanged()
                }
                return false
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100 && grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            prepare()
        } else {
            Toast.makeText(this@ContactActivity, "Permission is Denied", Toast.LENGTH_SHORT).show()
            checkPermission()
        }
    }
}