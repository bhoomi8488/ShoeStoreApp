package com.example.shoestore.screens.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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

        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_login, container, false)
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Setting binding params
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.btnLogin.setOnClickListener {
            if (viewModel.performValidation()) {
                val action =
                    LoginFragmentDirections.actionLoginFragmentToListFragment("", ",", "", "")
                findNavController().navigate(action)
            }
        }

        binding.btnCreate.setOnClickListener {
            if (viewModel.performValidation()) {
                savePreference()

                val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                findNavController().navigate(action)
            }
        }


        // Watching for login result
        viewModel.getLogInResult().observe(this.viewLifecycleOwner, Observer { result ->
            Log.i("observer", "loginreslut===" + result)

            Toast.makeText(activity, result, Toast.LENGTH_SHORT).show()
            //Toast.makeText(this, result, Toast.LENGTH_SHORT).show()

        })

        /*
           // Watching for login result
           viewModel.getIsLogin().observe(this.viewLifecycleOwner, Observer { isLogin ->
               Log.i("observer", "login status=== " + isLogin)

              if(isLogin){
                   val action =  LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                   findNavController().navigate(action)
               }
           })*/

        return binding.root
    }

    fun savePreference() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(USER_EMAIL, viewModel.email)
            putString(USER_PASSWORD, viewModel.password)
            putBoolean(LOGIN_SUCCESSFUL, viewModel.getIsLogin().value ?: false)
            apply()
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}