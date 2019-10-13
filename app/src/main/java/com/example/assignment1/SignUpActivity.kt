package com.example.assignment1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private var profileDB: ProfileDB? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        profileDB = ProfileDB.getInstance(this)

        sign_up_cancel.setOnClickListener {
            finish()
        }

        sign_up_with_check.setOnClickListener {
            val profile = Profile()
            profile.username = sign_up_username.text.toString()
            profile.password = sign_up_password.text.toString()
            profile.name = sign_up_name.text.toString()
            profile.phoneNumber = sign_up_phone_number.text.toString()
            profile.address = sign_up_address.text.toString()

            if (!ProfileVerify.verifyUsername(profile.username, profileDB, sign_up_username_message)) {
                sign_up_username.requestFocus()
                return@setOnClickListener
            } else if (!ProfileVerify.verifyPassword(profile.password, sign_up_password_message)) {
                sign_up_password.requestFocus()
                return@setOnClickListener
            } else if (!ProfileVerify.verifyName(profile.name, sign_up_name_message)) {
                sign_up_name.requestFocus()
                return@setOnClickListener
            } else if (!ProfileVerify.verifyPhoneNumber(profile.phoneNumber, sign_up_phone_number_message)) {
                sign_up_phone_number.requestFocus()
                return@setOnClickListener
            } else if (!ProfileVerify.verifyAddress(profile.address, sign_up_address_message)) {
                sign_up_address.requestFocus()
                return@setOnClickListener
            }

            var thread = Thread(Runnable {
                profileDB?.profileDao()?.insert(profile)
            })
            thread.start()
            finish()
        }

        term_of_service_agreement.setOnCheckedChangeListener { radioGroup, id ->
            if (term_of_service_agree.isChecked) {
                sign_up_with_check.isClickable = true
                sign_up_with_check.setBackgroundColor(getColor(R.color.light_blue))
            } else {
                sign_up_with_check.isClickable = false
                sign_up_with_check.setBackgroundColor(getColor(R.color.light_grey))
            }
        }
    }
}