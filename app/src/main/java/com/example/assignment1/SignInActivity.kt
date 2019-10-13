package com.example.assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        var profileDB = ProfileDB.getInstance(this)

        button_sign_in.setOnClickListener{
            val intent = Intent(this, ProfileConfigure::class.java)
            var success = false
            var username = sign_in_username.text.toString()
            var password = sign_in_password.text.toString()

            var signInThread = Thread(Runnable {
                success = profileDB?.profileDao()?.signInCheck(username, password)!!
            })
            signInThread.start()
            signInThread.join()

            if(!success){
                sign_in_message.isVisible = true;
                sign_in_message.text = "Please check your username and password."
                return@setOnClickListener
            }
            sign_in_message.isVisible = false
            Toast.makeText(applicationContext, "Hello, %s!".format(username), Toast.LENGTH_LONG).show()

            intent.putExtra("username", username)
            startActivity(intent)
        }

        button_sign_up.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
