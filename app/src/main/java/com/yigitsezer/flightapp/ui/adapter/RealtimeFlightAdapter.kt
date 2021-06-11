package com.yigitsezer.flightapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yigitsezer.flightapp.R
import com.yigitsezer.flightapp.repository.model.FlightData
import java.text.SimpleDateFormat
import java.util.Locale

class RealtimeFlightAdapter(private val dataSet: List<FlightData>, var onItemClickedListener: OnItemClickedListener) :
    RecyclerView.Adapter<RealtimeFlightAdapter.ViewHolder>() {

    val dateFormat = SimpleDateFormat("h:mm", Locale.getDefault())

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val airlineLabel: TextView
        val departureLabel: TextView
        val arrivalLabel: TextView
        val statusIcon: ImageView

        init {
            airlineLabel = view.findViewById(R.id.tv_airline_label)
            departureLabel = view.findViewById(R.id.tv_departure_label)
            arrivalLabel = view.findViewById(R.id.tv_arrival_label)
            statusIcon = view.findViewById(R.id.iv_status)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.flight_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentItem = dataSet[position]
        viewHolder.airlineLabel.text = currentItem.airline?.name.toString()
        viewHolder.departureLabel.text = "${currentItem.departure?.icao.toString()} - ${dateFormat.format(currentItem.departure?.estimated)}"
        viewHolder.arrivalLabel.text = "${currentItem.arrival?.icao.toString()} - ${dateFormat.format(currentItem.arrival?.estimated)}"
        when (currentItem.flight_status) {
            "active" -> viewHolder.statusIcon.setImageResource(R.drawable.status_circle_green)
            "scheduled" -> viewHolder.statusIcon.setImageResource(R.drawable.status_circle_yellow)
            else -> viewHolder.statusIcon.setImageResource(R.drawable.status_circle_red)
        }
        viewHolder.itemView.setOnClickListener {
            onItemClickedListener.onClick(position)
        }
    }

    override fun getItemCount() = dataSet.size

}

interface OnItemClickedListener {
    fun onClick(position: Int)
}