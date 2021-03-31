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
        setHasOptionsMenu(true)
        var binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail, container, false
        )

        viewModel = ViewModelProvider(this).get(ShoeDetailViewModel::class.java)

        // Setting binding params
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val args: ShoeDetailFragmentArgs by navArgs()

        viewModel.previousList = args.listOfShoe

        binding.btnSave.setOnClickListener {
            if (viewModel.performValidation()) {
                val action =
                    ShoeDetailFragmentDirections.actionShoeDetailFragmentToListFragment(viewModel.getShoe())
                findNavController().navigate(action)
            }
        }

        binding.btnCancel.setOnClickListener {
            val action =
                ShoeDetailFragmentDirections.actionShoeDetailFragmentToListFragment("")
            findNavController().navigate(action)
        }

        viewModel.getsaveResult().observe(this.viewLifecycleOwner, Observer { result ->
            Log.i("observer", "login status=== " + result)
            Toast.makeText(activity, result, Toast.LENGTH_SHORT).show()
        })

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