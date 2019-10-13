package com.example.assignment1

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile_configure.*

class ProfileConfigure : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_configure)

        var username = intent.extras!!.get("username") as String
        var password = ""
        var profileDB = ProfileDB.getInstance(this)
        var profile:Profile? = null
        var profileThread = Thread(Runnable {
            profile = profileDB?.profileDao()?.getProfile(username)!!
        })
        var changeThread = Thread(Runnable {
            profileDB?.profileDao()?.updatePassword(username, password)
        })
        profileThread.start()
        profileThread.join()

        profile_configure_username.text = profile!!.username
        profile_configure_name.text = profile!!.name
        profile_configure_phone_number.text = profile!!.phoneNumber
        profile_configure_address.text = profile!!.address

        profile_configure_password_change.setOnClickListener {
            password = profile_configure_password.text.toString()
            if(!ProfileVerify.verifyPassword(password, profile_configure_password_change_message)){
                profile_configure_password_change.requestFocus()
                return@setOnClickListener
            }
            changeThread.start()
            Toast.makeText(applicationContext, "Password Change Success!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}