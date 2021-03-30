package com.example.shoestore.screens.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeDetailBinding
import com.example.shoestore.screens.instruction.InstructionFragmentDirections

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailFragment : Fragment() {

    private lateinit var viewModel: ShoeDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_shoe_detail, container, false)

        var binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail, container, false
        )

        viewModel = ViewModelProvider(this).get(ShoeDetailViewModel::class.java)

        // Setting binding params
        binding.lifecycleOwner = this
        binding.viewModel = viewModel



        binding.btnSave.setOnClickListener {
            if (viewModel.performValidation()) {
                val action = ShoeDetailFragmentDirections.actionShoeDetailFragmentToListFragment(
                    viewModel.shoeName,
                    viewModel.shoeSize,
                    viewModel.compney,
                    viewModel.description
                )
                findNavController().navigate(action)
            }
        }

        binding.btnCancel.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shoeDetailFragment_to_listFragment)
        )

        viewModel.getsaveResult().observe(this.viewLifecycleOwner, Observer { result ->
            Log.i("observer", "login status=== " + result)
            Toast.makeText(activity, result, Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }


}