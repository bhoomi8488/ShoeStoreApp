package com.example.shoestore.screens.list

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.shoestore.MainActivity
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentListBinding
import com.example.shoestore.screens.login.LoginFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {


    lateinit var list: ListView

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list, container, false)

        val args: ListFragmentArgs by navArgs()


        val binding: FragmentListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        binding.viewModel = viewModel

        val listData: String =
            viewModel.getShoe(args.size, args.company, args.name, args.description)

        binding.fab.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_listFragment_to_shoeDetailFragment)
        )

        if (listData.isNotBlank()) {
            addTextView(binding.linList, listData)
            addTextView(binding.linList, viewModel.getDummyText())
            savePreference(listData)
        }

        //list =  binding.listView
        // list.adapter = activity?.baseContext?.applicationContext?.let { ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, viewModel.listViewArray) } as ListAdapter


        //viewModel.getListViewSize(list)


        return binding.root
    }


    fun addTextView(linLayout: LinearLayout, detail: String) {
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
    }

    fun savePreference(detail: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("detail", detail)
            apply()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.shoe_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.menuLogout -> {
                Log.i("debug", "activity: action share has clicked")
                savePreference()
                //  getActivity()?.getSupportFragmentManager()?.beginTransaction()?.remove(this)?.commit();
                startActivity(Intent(activity, MainActivity::class.java))
                getActivity()?.finishAffinity();
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun savePreference() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean("Status", false)
            putString("Email", "")
            putString("Password", "")
            apply()
        }

    }
}