package com.example.shoestore.screens.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shoestore.MainActivity
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentListBinding

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


    private lateinit var viewModel: ListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("List Fragment==", "Created")
    }

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

        val listData: String = args.listDetail

        binding.fab.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToShoeDetailFragment(listData)

            findNavController().navigate(action)
        }

        if (listData.isNotBlank()) {
            viewModel.addInList(binding.linList, listData, activity)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.shoe_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.menuLogout -> {
                val i = activity as MainActivity
                i.savePreference()
                startActivity(Intent(activity, MainActivity::class.java))
                getActivity()?.finishAffinity();
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        Log.i("List fragment", "==Destroyed")
        super.onDestroy()
    }
}