package com.example.assignment1

import android.util.Log
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import java.security.acl.Owner
import java.util.regex.Pattern

object ProfileVerify{
    val numberPattern = Pattern.compile("[0-9]+")
    val alphabetPattern = Pattern.compile("[a-zA-Z]+")
    val symbolPattern = Pattern.compile("[!@#$%^&*()+]+")
    val usernamePattern = Pattern.compile("[a-zA-Z0-9]+")
    val passwordPattern = Pattern.compile("[a-zA-Z0-9!@#$%^&*()+]+")
    val phoneNumberPattern = Pattern.compile("[0-9]{11}")

    fun verifyUsername(username: String, profileDB: ProfileDB?, message: TextView): Boolean{
        var usernameExist = false
        var validationCase = ValidationCase.VALID
        if(username.isEmpty())
            validationCase = ValidationCase.EMPTY_FIELD
        else if(username.length < 6)
            validationCase = ValidationCase.USERNAME_UNDER_MINIMUM_CHARACTER

        var alphbet = false
        var number = false
        for(c in username) {
            alphbet = alphbet || alphabetPattern.matcher(c.toString()).matches()
            number = number || numberPattern.matcher(c.toString()).matches()
        }
        if(!alphbet || !number || !usernamePattern.matcher(username).matches())
            validationCase = ValidationCase.USERNAME_MISMATCH


        if(validationCase.isValid()) {
            var usernameThread = Thread(Runnable {
                usernameExist = profileDB?.profileDao()?.isExistUsername(username)!!
            })
            usernameThread.start()
            usernameThread.join()
            if(usernameExist)
                validationCase = ValidationCase.USERNAME_EXIST
        }

        message.text = validationCase.getMnemonic()
        message.isVisible = !validationCase.isValid()
        return validationCase.isValid()
    }

    fun verifyPassword(password: String, message: TextView): Boolean{
        var validationCase = ValidationCase.VALID
        if(password.isEmpty())
            validationCase = ValidationCase.EMPTY_FIELD
        else if(password.length < 9)
            validationCase = ValidationCase.PASSWORD_UNDER_MINIMUM_CHARACTER

        var alphbet = false
        var number = false
        var symbol = false
        for(c in password) {
            alphbet = alphbet || alphabetPattern.matcher(c.toString()).matches()
            number = number || numberPattern.matcher(c.toString()).matches()
            symbol = symbol || symbolPattern.matcher(c.toString()).matches()
        }
        if(!alphbet || !number || !symbol || !passwordPattern.matcher(password).matches())
            validationCase = ValidationCase.PASSWORD_MISMATCH

        message.text = validationCase.getMnemonic()
        message.isVisible = !validationCase.isValid()
        return validationCase.isValid()
    }

    fun verifyName (name: String, message: TextView): Boolean{
        var validationCase = ValidationCase.VALID
        if(name.isEmpty())
            validationCase = ValidationCase.EMPTY_FIELD
        else if(!usernamePattern.matcher(name).matches())
            validationCase = ValidationCase.PHONE_NUMBER_MISMATCH

        message.text = validationCase.getMnemonic()
        message.isVisible = !validationCase.isValid()
        return validationCase.isValid()
    }

    fun verifyPhoneNumber(phoneNumber: String, message: TextView): Boolean{
        var validationCase = ValidationCase.VALID
        if(phoneNumber.isEmpty())
            validationCase = ValidationCase.EMPTY_FIELD
        else if(!phoneNumberPattern.matcher(phoneNumber).matches())
            validationCase = ValidationCase.PHONE_NUMBER_MISMATCH

        message.text = validationCase.getMnemonic()
        message.isVisible = !validationCase.isValid()
        return validationCase.isValid()
    }

    fun verifyAddress(address: String, message: TextView): Boolean{
        var validationCase = ValidationCase.VALID
        if(address.isEmpty())
            validationCase = ValidationCase.EMPTY_FIELD

        message.text = validationCase.getMnemonic()
        message.isVisible = !validationCase.isValid()
        return validationCase.isValid()
    }
}

enum class ValidationCase{
    VALID,
    EMPTY_FIELD,
    USERNAME_UNDER_MINIMUM_CHARACTER,
    USERNAME_MISMATCH,
    USERNAME_EXIST,
    PASSWORD_UNDER_MINIMUM_CHARACTER,
    PASSWORD_MISMATCH,
    PHONE_NUMBER_MISMATCH;

    fun getMnemonic(): String{
        var nmemonic = ""
        when(this){
            VALID -> nmemonic = ""
            EMPTY_FIELD -> nmemonic = "Please fill all information."
            USERNAME_UNDER_MINIMUM_CHARACTER -> nmemonic = "Username must consist of at least 6 characters."
            USERNAME_MISMATCH -> nmemonic = "Username must consist of alphabet and number."
            USERNAME_EXIST -> nmemonic = "Username is already exist."
            PASSWORD_UNDER_MINIMUM_CHARACTER -> nmemonic = "Password must contain at least 9 characters."
            PASSWORD_MISMATCH -> nmemonic = "Password must contain alphabet, number, and symbols(!,@,#,$,%,^,&,*,(,),+)"
            PHONE_NUMBER_MISMATCH -> nmemonic = "Phone number must consist of 11 numbers."
        }
        return nmemonic
    }

    fun isValid(): Boolean{
        return this == VALID
    }
}