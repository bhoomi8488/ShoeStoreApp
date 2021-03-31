package com.example.shoestore.screens.detail

import android.util.Log
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
    var previousList: String = ""

    private val saveResult = MutableLiveData<String>()

    fun getsaveResult(): LiveData<String> = saveResult

    fun performValidation(): Boolean {

        if (shoeName.isBlank()) {
            saveResult.value = "Enter Shoe Name"
            return false
        }

        if (compney.isBlank()) {
            saveResult.value = "Enter Compney"
            return false
        }
        if (shoeSize.isBlank()) {
            saveResult.value = "Enter Shoe Size"
            return false
        }
        if (description.isBlank()) {
            saveResult.value = "Enter Eescription"
            return false
        }
        saveResult.value = "Shoe Added."
        return true
    }

    fun getShoe(): String {

        val builder = StringBuilder()
        if (shoeSize.isNotBlank() && compney.isNotBlank() && shoeName.isNotBlank() && description.isNotBlank()) {
            builder.append("Shoe Name: ").append(shoeName)
            builder.append("\nCompany: ").append(compney)
            builder.append("\nShoe Size: ").append(shoeSize)
            builder.append("\nDescription: ").append(description)
            builder.append("\n")
            if (previousList.isNotBlank()) {
                builder.append("\n").append(previousList)
            }
            return builder.toString()
        } else {
            return ""
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ShoeDetailViewModel", "Shoe detail destroyed")
    }

}