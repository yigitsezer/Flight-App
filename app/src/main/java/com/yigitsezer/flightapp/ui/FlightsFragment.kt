package com.yigitsezer.flightapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.GsonBuilder
import com.yigitsezer.flightapp.R
import com.yigitsezer.flightapp.databinding.FragmentFlightsBinding
import com.yigitsezer.flightapp.repository.model.FlightData
import com.yigitsezer.flightapp.repository.model.RealtimeFlights
import com.yigitsezer.flightapp.repository.network.AviationStackService
import com.yigitsezer.flightapp.ui.adapter.OnItemClickedListener
import com.yigitsezer.flightapp.ui.adapter.RealtimeFlightAdapter
import com.yigitsezer.flightapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlightsFragment: Fragment() {

    var binding: FragmentFlightsBinding? = null
    var service: AviationStackService? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_flightsFragment_to_loginFragment)
                }
            })

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        service = Retrofit.Builder()
            .baseUrl("http://api.aviationstack.com/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AviationStackService::class.java)

        binding?.rvFlightList?.layoutManager = GridLayoutManager(requireContext(), 1)

        binding?.rvFlightList?.let { recyclerView ->
            service?.getRealtimeFlights(Constants.API_KEY)?.enqueue(object: Callback<RealtimeFlights> {
                override fun onResponse(
                    call: Call<RealtimeFlights>,
                    response: Response<RealtimeFlights>
                ) {
                    Log.d("HELLOW", "SUCCESS")
                    response.body()?.data?.let {
                        recyclerView.adapter = RealtimeFlightAdapter(it, object: OnItemClickedListener{
                            override fun onClick(position: Int) {
                                val action = FlightsFragmentDirections.actionFlightsFragmentToFlightDetailFragment(it[position])
                                findNavController().navigate(action)
                            }
                        })
                    }
                }

                override fun onFailure(call: Call<RealtimeFlights>, t: Throwable) {
                    Toast.makeText(requireContext(), "Bir hata ile karşılaşıldı", Toast.LENGTH_LONG).show()
                    Log.d("HELLOW", t.message.toString())
                }
            })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}