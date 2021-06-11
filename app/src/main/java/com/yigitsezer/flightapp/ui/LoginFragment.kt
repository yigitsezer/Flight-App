package com.yigitsezer.flightapp.ui

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yigitsezer.flightapp.R
import com.yigitsezer.flightapp.databinding.FragmentLoginBinding
import com.yigitsezer.flightapp.utils.Constants


class LoginFragment: Fragment() {
    var binding: FragmentLoginBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finishAndRemoveTask()
                }
            })

        val prefs = requireActivity().getSharedPreferences("Login Prefs", MODE_PRIVATE)
        val editor = prefs.edit()

            binding?.let {
            if (prefs.getBoolean("isRemember", false)) {
                it.tfEmail.editText?.setText(prefs.getString("email", "").toString())
                //it.tfEmail.editText?.setText(prefs.getString("email", "").toString())
                //it.cbRemember.isChecked = true
                editor.putBoolean("isRemember", false)
                editor.remove("email")
                editor.remove("password")
                editor.apply()
            }

            it.btnLogin.setOnClickListener { button ->
                if (it.tfEmail.editText?.text.toString() == Constants.EMAIL &&
                    it.tfPassword.editText?.text.toString() == Constants.PASSWORD) {
                    findNavController().navigate(R.id.flightsFragment)
                    if (it.cbRemember.isChecked) {
                        editor.putBoolean("isRemember", true)
                        editor.putString("email", it.tfEmail.editText?.text.toString())
                        editor.putString("password", it.tfPassword.editText?.text.toString().toString())
                        editor.apply()
                    } else {
                        editor.putBoolean("isRemember", false)
                        editor.remove("email")
                        editor.remove("password")
                        editor.apply()
                    }
                } else {
                    Toast.makeText(requireContext(), "Bilgileriniz hatalıdır. Lütfen bilgilerini kontrol ediniz", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}