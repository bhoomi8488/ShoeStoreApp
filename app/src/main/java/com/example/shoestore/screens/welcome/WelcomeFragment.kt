package com.example.shoestore.screens.welcome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shoestore.MainActivity
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentWelcomeBinding


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
        setHasOptionsMenu(true)
        //Navigation welcome fargment
        binding.btnNext.setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment()
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