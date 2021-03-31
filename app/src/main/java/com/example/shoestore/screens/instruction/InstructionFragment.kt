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
        //return inflater.inflate(R.layout.fragment_instruction, container, false)
        setHasOptionsMenu(true)
        val binding: FragmentInstructionBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_instruction, container, false
        )

        binding.btnShoeList.setOnClickListener {

            val action = InstructionFragmentDirections.actionInstructionFragmentToListFragment("")
            findNavController().navigate(action)
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
}