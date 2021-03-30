package com.example.shoestore.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Bhoomi on 3/25/2021.
 */

class LoginViewModel() : ViewModel() {


    var email: String = ""
    var password: String = ""

    private var isLogin = MutableLiveData<Boolean>()

    fun getIsLogin(): LiveData<Boolean> = isLogin

    /**
     * To pass login result to activity
     */
    private val logInResult = MutableLiveData<String>()

    fun getLogInResult(): LiveData<String> = logInResult

    init {
        isLogin.value = false
        performValidation()
    }

    /**
     * Called from activity on login button click
     */
    fun performValidation(): Boolean {

        if (email.isBlank()) {
            logInResult.value = "Invalid username"
            return false
        }

        if (password.isBlank()) {
            logInResult.value = "Invalid password"
            return false
        }
        isLogin.value = true
        logInResult.value = "Valid credentials :)"
        return true
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("LoginViewModel", "Login ViewModel destroyed")
    }


    fun onLogInClicked() {

        performValidation()
    }
}