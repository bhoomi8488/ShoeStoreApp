package com.example.shoestore.screens.list

import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Bhoomi on 3/29/2021.
 */
class ListViewModel : ViewModel() {

    data class ShoeList(
        val shoeSize: Int,
        val company: String,
        val shoeName: String,
        val description: String
    )

    private val shoe = MutableLiveData<String>()
    fun getShoeDetail(): LiveData<String> = shoe


    private fun savePreference(detail: String, activity: FragmentActivity?) {
        //Log.i("detail==","$detail")

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("detail", detail)
            apply()
        }
    }

    fun savePreference(activity: FragmentActivity?) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean("Status", false)
            putString("Email", "")
            putString("Password", "")
            putString("detail", "")
            apply()
        }

    }

    fun addInList(
        linLayout: LinearLayout,
        detail: String,
        activity: FragmentActivity?
    ) {

        val textView = TextView(activity)
        textView.text = detail
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        textView.layoutParams = params
        textView.textSize = 16F
        textView.setTextColor(Color.BLACK)
        linLayout.addView(textView)
        savePreference(detail, activity)
    }


}