package com.example.shoestore.screens.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentLoginBinding


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    companion object {
        private val LOGIN_SUCCESSFUL = "Status"
        private val USER_EMAIL = "Email"
        private val USER_PASSWORD = "Password"
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val preference = activity?.getPreferences(Context.MODE_PRIVATE)
        val storeList = preference?.getString("detail", "")
        val email = preference?.getString(USER_EMAIL, "")
        val password = preference?.getString(USER_PASSWORD, "")
        val status = preference?.getBoolean(LOGIN_SUCCESSFUL, false)

        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_login, container, false)
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.email = email!!
        viewModel.password = password!!

        // Setting binding params
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //Login with create account and navigate to list Fragment
        binding.btnLogin.setOnClickListener {
            if (status!!) {
                if (viewModel.performValidation()) {
                    val action =
                        LoginFragmentDirections.actionLoginFragmentToListFragment()
                    findNavController().navigate(action)
                }
            } else {
                Toast.makeText(activity, "Create the account first.", Toast.LENGTH_SHORT).show()
            }
        }

        //Handle the on create the button event, validate the data and store into savePreference
        //Navigate to welcome fragment
        binding.btnCreate.setOnClickListener {
            if (viewModel.performValidation()) {
                savePreference()
                val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                findNavController().navigate(action)
            }
        }
        // Watching for login result
        viewModel.getLogInResult().observe(this.viewLifecycleOwner, Observer { result ->
            Toast.makeText(activity, result, Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }

    //save the login detail into savePreference
    private fun savePreference() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(USER_EMAIL, viewModel.email)
            putString(USER_PASSWORD, viewModel.password)
            putBoolean(LOGIN_SUCCESSFUL, viewModel.getIsLogin().value ?: false)
            apply()
        }

    }

}