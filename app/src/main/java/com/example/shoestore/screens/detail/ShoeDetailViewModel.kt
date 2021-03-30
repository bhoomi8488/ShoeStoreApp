package com.example.shoestore.screens.detail

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Bhoomi on 3/27/2021.
 */
class ShoeDetailViewModel : ViewModel() {

    var shoeName: String = ""
    var compney: String = ""
    var shoeSize: String = ""
    var description: String = ""

    private val saveResult = MutableLiveData<String>()

    fun getsaveResult(): LiveData<String> = saveResult

    private var isValue = MutableLiveData<Boolean>()

    fun getIsValue(): LiveData<Boolean> = isValue


    fun performValidation(): Boolean {

        if (shoeName.isBlank()) {
            saveResult.value = "Enter Shoe Name"
            isValue.value = false
            return false
        }

        if (compney.isBlank()) {
            saveResult.value = "Enter Compney"
            isValue.value = false
            return false
        }
        if (shoeSize.isBlank()) {
            saveResult.value = "Enter Shoe Size"
            isValue.value = false
            return false
        }
        if (description.isBlank()) {
            saveResult.value = "Enter Eescription"
            isValue.value = false
            return false
        }
        saveResult.value = "Shoe Added."
        isValue.value = true
        return true
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("LoginViewModel", "Login ViewModel destroyed")
    }

}