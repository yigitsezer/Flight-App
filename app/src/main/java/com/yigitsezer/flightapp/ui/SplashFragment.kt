package com.yigitsezer.flightapp.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yigitsezer.flightapp.R
import com.yigitsezer.flightapp.databinding.FragmentSplashBinding
import com.yigitsezer.flightapp.utils.Constants

class SplashFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return FragmentSplashBinding.inflate(layoutInflater, container, false).root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = requireActivity().getSharedPreferences("Login Prefs", Context.MODE_PRIVATE)

        if (prefs.getBoolean("isRemember", false)) {
            if (prefs.getString("email", "") == Constants.EMAIL &&
                prefs.getString("password", "") == Constants.PASSWORD) {
                findNavController().navigate(R.id.action_splashFragment_to_flightsFragment)
            }
        } else {
            view.postDelayed({
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }, 2000)
        }


    }
}