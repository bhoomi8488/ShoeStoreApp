package com.example.shoestore.screens.instruction

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.shoestore.MainActivity
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentInstructionBinding
import com.example.shoestore.screens.welcome.WelcomeFragmentDirections


/**
 *
 * Created by Bhoomi on 3/29/2021.
 * A simple [Fragment] subclass.
 * Use the [InstructionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InstructionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentInstructionBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_instruction, container, false
        )
        //Navidate from Insrtuction to List Fragment
        binding.btnShoeList.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_instructionFragment_to_listFragment)
        )
        return binding.root
    }
}