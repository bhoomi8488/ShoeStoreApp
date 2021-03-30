package com.example.shoestore.screens.list

import android.content.ClipData
import android.util.Log
import android.widget.ListAdapter
import android.widget.ListView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore.dummy.DummyContent
import java.util.ArrayList

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


    val listViewArray = arrayOf(
        "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE",
        "TEN", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen"
    )

    fun getShoe(shoeSize: String, company: String, shoeName: String, description: String): String {

        if (shoeSize.isNotBlank() && company.isNotBlank() && shoeName.isNotBlank() && description.isNotBlank()) {
            val builder = StringBuilder()
            builder.append("Shoe Name: ").append(shoeName)
            builder.append("\nCompany: ").append(company)
            builder.append("\nShoe Size: ").append(shoeSize)
            builder.append("\nDescription: ").append(description)
            builder.append("\n")
            return builder.toString()
        } else {
            return ""
        }
    }

    fun getDummyText(): String {

        val str = "Shoe Name: Nike \nCompany: Nike\nShoe Size: 5\nDescription: very good"
        return str
    }


    fun getListViewSize(
        myListView: ListView
    ): ListView {
        val myListAdapter: ListAdapter = myListView.adapter
            ?: //do nothing return null
            return myListView
        //set listAdapter in loop for getting final size
        var totalHeight = 0
        for (size in 0 until myListAdapter.count) {
            val listItem = myListAdapter.getView(size, null, myListView)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }
        //setting listView item in adapter
        val params = myListView.layoutParams
        params.height =
            totalHeight + myListView.dividerHeight * (myListAdapter.count - 1)
        myListView.layoutParams = params


        return myListView
        // print height of adapter on log
        Log.i("height of listItem:", totalHeight.toString())
    }


}