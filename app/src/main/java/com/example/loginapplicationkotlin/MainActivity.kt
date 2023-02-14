package com.example.loginapplicationkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var uiEtUserName:EditText? = null
    private var uiEtUserEmail:EditText? = null
    private var uiEtUserPassword:EditText? = null
    private var uiButtonSubmit: Button? = null

    private val sharedPreferences: MySharedPreferences by lazy {
        MySharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
        setUpGender()
        checkPreviousFieldSavedInDb()
        setUpOnClickListeners()
    }

    private fun checkPreviousFieldSavedInDb() {
        if (sharedPreferences.getUserName() != null) {
            navigateToNextScreen()
            finish()
        }
    }

    private fun setUpUi() {
        uiEtUserName = findViewById(R.id.uiEtUserName)
        uiEtUserEmail = findViewById(R.id.uiEtUserEmail)
        uiEtUserPassword= findViewById(R.id.uiEtPassword)
        uiButtonSubmit = findViewById(R.id.uiButtonSubmit)

    }

    private fun setUpGender() {
        val spinner = findViewById<Spinner>(R.id.uiSpinnerGender)
        val adapter = ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = null
    }

    private fun validateName(): Boolean {
        return if (uiEtUserName?.text.toString().isEmpty()) {
            uiEtUserName?.error = getString(R.string.userNameErrorMessage)
            uiEtUserName?.requestFocus()
            false
        } else {
            uiEtUserName?.error = null
            true
        }
    }

    private fun validateEmail(): Boolean {
        val emailToString: String = uiEtUserEmail?.text.toString()
        return if (emailToString.isEmpty()) {
            uiEtUserEmail?.error = getString(R.string.emailErrorMessage)
            uiEtUserEmail?.requestFocus()
            false
        } else if ((emailToString.isNotEmpty()) && (!Patterns.EMAIL_ADDRESS.matcher(emailToString)
                .matches())) {
            uiEtUserEmail?.error = getString(R.string.emailFormatErrorMessage)
            false
        } else {
            uiEtUserEmail?.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val passwordToString: String = uiEtUserPassword!!.text.toString()
        if(passwordToString.isEmpty()) {
            uiEtUserPassword?.error = getString(R.string.passwordErrorMessage)
            uiEtUserPassword?.requestFocus()
            return false
        }
        return if(passwordToString.isNotEmpty()) {
            if(passwordToString.length <7) {
                uiEtUserPassword?.error = getString(R.string.passwordStrengthErrorMessage)
                uiEtUserPassword?.requestFocus()
                false
            } else {
                uiEtUserPassword?.error = null
                true
            }
        } else {
            uiEtUserPassword?.error = getString(R.string.passwordStrengthErrorMessage)
            false
        }
    }

    private fun submitAction() {
        if(validateName() && validateEmail() && validatePassword()) {
            saveInputFieldsToDb()
            navigateToNextScreen()
        }
    }

    private fun saveInputFieldsToDb() {

        val userName: String = uiEtUserName?.text.toString().trim()
        val userEmail: String = uiEtUserEmail?.text.toString().trim()
        val userPassword: String = uiEtUserPassword?.text.toString().trim()
        val userGender: String = uiSpinnerGender?.selectedItem.toString()

        sharedPreferences.setUserName(userName)
        sharedPreferences.setUserEmail(userEmail)
        sharedPreferences.setUserPassword(userPassword)
        sharedPreferences.setUserGender(userGender)
    }

    private fun navigateToNextScreen() {
        val intent = Intent(this@MainActivity, LoggedInActivity::class.java)
        startActivity(intent)
    }

    private fun setUpOnClickListeners() {
        uiButtonSubmit?.setOnClickListener {
            submitAction()
        }
    }
}