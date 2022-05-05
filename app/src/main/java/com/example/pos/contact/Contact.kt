package com.example.pos.contact

class Contact {

    var name: String? = null
    var phone: String? = null

    @JvmName("getName1")
    fun getName(): String? {
        return name
    }

    @JvmName("setName1")
    fun setName(name: String?) {
        this.name = name
    }

    @JvmName("getPhone1")
    fun getPhone(): String? {
        return phone
    }

    @JvmName("setPhone1")
    fun setPhone(phone: String?) {
        this.phone = phone
    }
}