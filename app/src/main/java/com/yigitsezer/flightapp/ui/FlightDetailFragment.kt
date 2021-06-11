package com.yigitsezer.flightapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yigitsezer.flightapp.databinding.FragmentFlightDetailBinding
import java.text.SimpleDateFormat
import java.util.Locale

class FlightDetailFragment: Fragment() {

    var binding: FragmentFlightDetailBinding? = null

    val args: FlightDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentFlightDetailBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val flightInformation = args.flightInformation
        binding?.let {
            val dateFormat = SimpleDateFormat("EEE, MMM d, h:mm a", Locale.getDefault())

            it.ivFlightDetailsTitle.text = flightInformation?.flight?.icao + " Flight Details"

            it.ticket.tvDepartureAirfield.text = flightInformation?.departure?.icao
            it.ticket.tvDepartureAirfieldDetailed.text = flightInformation?.departure?.airport
            it.ticket.tvDepartureTime.text = dateFormat.format(flightInformation?.departure?.scheduled)

            it.ticket.tvArrivalAirfield.text = flightInformation?.arrival?.icao
            it.ticket.tvArrivalAirfieldDetailed.text = flightInformation?.arrival?.airport
            it.ticket.tvArrivalTime.text = dateFormat.format(flightInformation?.arrival?.scheduled)

            it.ticket.tvFlightCode.text = flightInformation?.flight?.icao
            it.ticket.tvTerminal.text = flightInformation?.departure?.terminal
            it.ticket.tvGate.text = flightInformation?.departure?.gate
            if (flightInformation?.departure?.delay == null)
                it.ticket.tvDelay.text = "0"
            else it.ticket.tvDelay.text = flightInformation.departure?.delay.toString()

            it.ivDetailClose.setOnClickListener {
                findNavController().popBackStack()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}