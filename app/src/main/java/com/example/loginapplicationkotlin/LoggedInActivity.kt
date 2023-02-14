package com.example.loginapplicationkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_logged_in.*

class LoggedInActivity : AppCompatActivity() {

    private var uiTvEnteredName: TextView? = null
    private var uiTvEnteredEmail: TextView? = null
    private var uiTvEnteredPassword: TextView? = null
    private var uiTvEnteredGender: TextView? = null
    private var uiButtonLogout:Button? = null
    private val sharedPreferences: MySharedPreferences by lazy {
        MySharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        setUserUI()
        displayInputFields()
        setUpListeners()
    }

    private fun setUserUI() {
        uiTvEnteredName = findViewById(R.id.uiTvEnteredName)
        uiTvEnteredEmail = findViewById(R.id.uiTvEnteredEmail)
        uiTvEnteredPassword = findViewById(R.id.uiTvEnteredPassword)
        uiTvEnteredGender = findViewById(R.id.uiTvEnteredGender)
        uiButtonLogout = findViewById(R.id.uiButtonLogout)
    }

    private fun displayInputFields() {
        uiTvEnteredName?.text = sharedPreferences.getUserName()
        uiTvEnteredEmail?.text = sharedPreferences.getUserEmail()
        uiTvEnteredPassword?.text = sharedPreferences.getUserPassword()
        uiTvEnteredGender?.text = sharedPreferences.getUserGender()
    }

    private fun setUpListeners() {
        uiButtonLogout?.setOnClickListener {
            actionSubmit()
        }
    }

    private fun actionSubmit() {
        sharedPreferences.clearData()
        navigateToNextScreen()
        finish()
    }

    private fun navigateToNextScreen() {
        val intent = Intent(this@LoggedInActivity, MainActivity::class.java)
        startActivity(intent)
    }
}
