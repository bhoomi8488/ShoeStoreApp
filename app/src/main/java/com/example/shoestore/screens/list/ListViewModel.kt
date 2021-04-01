package com.example.shoestore.screens.list

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.shoestore.screens.detail.ShoeDetailFragmentDirections

/**
 * Created by Bhoomi on 3/29/2021.
 *
 * ListviewModel for the list of shoe and also shared the data between ListFragment and ShowDetailFragment
 *Save the shoelist in save preference
 *
 *
 */
class ListViewModel : ViewModel() {

    //Observe the data for the validation
    private val _shoe = MutableLiveData<String>()
    fun getShoeDetail(): LiveData<String> = _shoe

    //Observe the data for the list
    private var _ShoeListLive: MutableLiveData<MutableList<String>> = MutableLiveData()
    fun getShoeListLive(): LiveData<MutableList<String>> = _ShoeListLive

    // Add the Shoe detail in  list
    private var list = ArrayList<String>()
    fun addShoeToList(shoe: String) {
        list.add(shoe)
        _ShoeListLive.value = list
    }

    //Decalre  Edittext Field
    var shoeName: String = ""
    var compney: String = ""
    var shoeSize: String = ""
    var description: String = ""

    private val saveResult = MutableLiveData<String>()

    fun getsaveResult(): LiveData<String> = saveResult

    // Validate the value of shoe detail
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
            saveResult.value = "Enter description"
            return false
        }
        addShoeToList(getShoe())
        return true
    }

    //builder the string for the shoe list according to shoe detail fragment
    fun getShoe(): String {
        val builder = StringBuilder()
        if (shoeSize.isNotBlank() && compney.isNotBlank() && shoeName.isNotBlank() && description.isNotBlank()) {
            builder.append("Shoe Name: ").append(shoeName)
            builder.append("\nCompany: ").append(compney)
            builder.append("\nShoe Size: ").append(shoeSize)
            builder.append("\nDescription: ").append(description)
            builder.append("\n")
            return builder.toString()
        } else {
            return ""
        }
    }

    //save the list in savePreference
    private fun savePreference(detail: String, activity: FragmentActivity?) {

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("detail", detail)
            apply()
        }
    }

    //add the new shoe detail in the list also Textview added into ListFragment UI
    fun addInList(
        linLayout: LinearLayout,
        detail: MutableList<String>,
        activity: FragmentActivity?
    ) {

        for (list in detail) {
            val textView = TextView(activity)
            textView.text = list
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            textView.layoutParams = params
            textView.textSize = 16F
            textView.setTextColor(Color.BLACK)
            linLayout.addView(textView)
            savePreference(list, activity)
            back()
        }
    }

    //Clear the shoe detail fields
    fun back() {
        Log.i("list view clear", "dff")
        shoeName = ""
        compney = ""
        shoeSize = ""
        description = ""
    }

}