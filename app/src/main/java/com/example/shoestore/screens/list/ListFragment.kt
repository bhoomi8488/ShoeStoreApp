package com.example.shoestore.screens.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.shoestore.MainActivity
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentListBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Set the menu option in toolbar
        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        val binding: FragmentListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        //Initialize the viewModel object
        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)

        //Binding the viewModel
        binding.viewModel = viewModel

        //Click on floating action button navigate to List Fragment to Shoe detail Fragment
        binding.fab.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_listFragment_to_shoeDetailFragment)
        )

        //Observe the data for shoe list
        viewModel.getShoeListLive().observe(viewLifecycleOwner, Observer {
            viewModel.addInList(binding.linList, it, activity)
        })

        return binding.root
    }

    //Create the option menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.shoe_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //Handle the select menu item event
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
}