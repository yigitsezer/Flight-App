package com.yigitsezer.flightapp.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Pagination (
    @SerializedName("limit")
    var limit: Int?,

    @SerializedName("offset")
    var offset: Int?,

    @SerializedName("count")
    var count: Int?,

    @SerializedName("total")
    var total: Int?
): Parcelable

@Parcelize
data class Departure (
    @SerializedName("airport")
    var airport: String?,

    @SerializedName("timezone")
    var timezone: String?,

    @SerializedName("iata")
    var iata: String?,

    @SerializedName("icao")
    var icao: String?,

    @SerializedName("terminal")
    var terminal: String?,

    @SerializedName("gate")
    var gate: String?,

    @SerializedName("delay")
    var delay: Int?,

    @SerializedName("scheduled")
    var scheduled: Date?,

    @SerializedName("estimated")
    var estimated: Date?,

    @SerializedName("actual")
    var actual: Date?,

    @SerializedName("estimated_runway")
    var estimated_runway: Date?,

    @SerializedName("actualy_runway")
    var actual_runway: Date?
): Parcelable

@Parcelize
data class Arrival (
    @SerializedName("airport")
    var airport: String?,

    @SerializedName("timezone")
    var timezone: String?,

    @SerializedName("iata")
    var iata: String?,

    @SerializedName("icao")
    var icao: String?,

    @SerializedName("terminal")
    var terminal: String?,

    @SerializedName("gate")
    var gate: String?,

    @SerializedName("baggage")
    var baggage: String?,

    @SerializedName("delay")
    var delay: Int?,

    @SerializedName("scheduled")
    var scheduled: Date?,

    @SerializedName("estimated")
    var estimated: Date?,

    @SerializedName("actual")
    var actual: Date?,

    @SerializedName("estimated_runway")
    var estimated_runway: Date?,

    @SerializedName("actualy_runway")
    var actual_runway: Date?
): Parcelable

@Parcelize
data class Airline (
    @SerializedName("name")
    var name: String?,

    @SerializedName("iata")
    var iata: String?,

    @SerializedName("icao")
    var icao: String?
): Parcelable

@Parcelize
data class Flight (
    @SerializedName("number")
    var number: String?,

    @SerializedName("iata")
    var iata: String?,

    @SerializedName("icao")
    var icao: String?,

    @SerializedName("codeshared")
    var codeshared: Codeshared?,
): Parcelable

@Parcelize
data class Codeshared (
    @SerializedName("airline_name")
    var airline_name: String?,

    @SerializedName("airline_iata")
    var airline_iata: String?,

    @SerializedName("airline_icao")
    var airline_icao: String?,

    @SerializedName("flight_number")
    var flight_number: String?,

    @SerializedName("flight_iata")
    var flight_iata: String?,

    @SerializedName("flight_icao")
    var flight_icao: String?,
): Parcelable

@Parcelize
class Aircraft (
    @SerializedName("registration")
    var registration: String?,

    @SerializedName("iata")
    var iata: String?,

    @SerializedName("icao")
    var icao: String?,

    @SerializedName("icao24")
    var icao24: String?
): Parcelable

@Parcelize
class Live (
    @SerializedName("updated")
    var updated: Date?,

    @SerializedName("latitude")
    var latitude: Float?,

    @SerializedName("longitude")
    var longitude: Float?,

    @SerializedName("altitude")
    var altitude: Float?,

    @SerializedName("direction")
    var direction: Float?,

    @SerializedName("speed_horizontal")
    var speed_horizontal: Float?,

    @SerializedName("speed_vertical")
    var speed_vertical: Float?,

    @SerializedName("is_ground")
    var is_ground: Boolean?
): Parcelable

@Parcelize
data class FlightData (
    @SerializedName("flight_date")
    var flight_date: String?,

    @SerializedName("flight_status")
    var flight_status: String?,

    @SerializedName("departure")
    var departure: Departure?,

    @SerializedName("arrival")
    var arrival: Arrival?,

    @SerializedName("airline")
    var airline: Airline?,

    @SerializedName("flight")
    var flight: Flight?,

    @SerializedName("aircraft")
    var aircraft: Aircraft?,

    @SerializedName("live")
    var live: Live?
): Parcelable

data class RealtimeFlights (
    @SerializedName("pagination")
    var pagination: Pagination?,

    @SerializedName("data")
    var data: List<FlightData>?
)



