package com.example.shoestore.screens.welcome

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentWelcomeBinding
import com.example.shoestore.screens.login.LoginFragmentDirections


/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_welcome, container, false)

        var binding: FragmentWelcomeBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_welcome, container, false
            )

        //Navigation welcome fargment
        binding.btnNext.setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment()
            findNavController().navigate(action)
        }


        return binding.root


    }

}