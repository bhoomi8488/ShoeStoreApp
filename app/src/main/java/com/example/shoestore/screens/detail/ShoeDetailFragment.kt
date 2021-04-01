package com.example.shoestore.screens.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shoestore.MainActivity
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeDetailBinding
import com.example.shoestore.screens.instruction.InstructionFragmentDirections
import com.example.shoestore.screens.list.ListViewModel
import com.example.shoestore.screens.login.LoginFragmentDirections

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail, container, false
        )

        //Initialize the view model
        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)

        // Setting binding params
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //Handle the validation on save button and navigate back to list fragment
        binding.btnSave.setOnClickListener {
            if (viewModel.performValidation()) {
                val action = ShoeDetailFragmentDirections.actionShoeDetailFragmentToListFragment()
                findNavController().navigate(action)
            }
        }

        //Navigate back to list fragment
        binding.btnCancel.setOnClickListener {
            viewModel.back()
            val action = ShoeDetailFragmentDirections.actionShoeDetailFragmentToListFragment()
            findNavController().navigate(action)
        }

        //Observe the shoe detail field validation
        viewModel.getsaveResult().observe(this.viewLifecycleOwner, Observer { result ->
            Toast.makeText(activity, result, Toast.LENGTH_SHORT).show()
        })
        return binding.root
    }
}