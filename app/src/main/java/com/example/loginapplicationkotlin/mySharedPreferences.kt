package com.example.loginapplicationkotlin

import android.content.Context
import android.content.SharedPreferences.*
import com.example.loginapplicationkotlin.Constants.SHARED_PREFERENCES

class MySharedPreferences(context: Context) {

    private val mySharedPreference = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
    private val editor:Editor? = mySharedPreference.edit()

    fun getUserName(): String? {
        return mySharedPreference.getString(Constants.KEY_NAME,null)
    }

    fun getUserEmail(): String? {
        return mySharedPreference.getString(Constants.KEY_EMAIL, null)
    }

    fun getUserPassword(): String? {
        return mySharedPreference.getString(Constants.KEY_PASSWORD, null)
    }

    fun getUserGender(): String? {
        return mySharedPreference.getString(Constants.KEY_GENDER, null)
    }

    fun setUserName(name: String?) {
        editor?.putString(Constants.KEY_NAME, name)?.apply()
    }

    fun setUserEmail(email: String?) {
        editor?.putString(Constants.KEY_EMAIL, email)?.apply()
    }

    fun setUserPassword(password: String?) {
        editor?.putString(Constants.KEY_PASSWORD, password)?.apply()
    }

    fun setUserGender(gender: String?) {
        editor?.putString(Constants.KEY_GENDER, gender)?.apply()
    }

    fun clearData() {
        editor?.clear()
        editor?.apply()
    }
}