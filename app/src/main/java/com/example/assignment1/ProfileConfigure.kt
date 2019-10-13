package com.example.assignment1

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile_configure.*

class ProfileConfigure : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_profile_configure)

        var username = intent.extras!!.get("username") as String
        var profileDB = ProfileDB.getInstance(this)
        var profile:Profile? = null
        var profileThread = Thread(Runnable {
            profile = profileDB?.profileDao()?.getProfile(username)!!
        })
        profileThread.start()
        profileThread.join()

        profile_configure_username.text = profile!!.username
    }
}