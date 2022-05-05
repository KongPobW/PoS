package com.example.pos.adapter_contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.R
import com.example.pos.contact.Contact

class ContactAdapter(private val conList: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    init {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_contact, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact: Contact = conList[position]

        holder.contactNameView.setText(contact.getName())
        holder.contactPhoneView.setText(contact.getPhone())
    }

    override fun getItemCount(): Int {
        return conList.size
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val contactNameView: TextView = ItemView.findViewById(R.id.contact_name)
        val contactPhoneView: TextView = ItemView.findViewById(R.id.contact_phone)
    }
}