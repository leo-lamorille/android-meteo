package com.llamorille.androidmeteo.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.llamorille.androidmeteo.R
import com.squareup.picasso.Picasso

class DetailFragment: Fragment() {
    val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather = args.weather
        Log.d("DETAIL", weather.toString())

        // Conditions
        val condition = weather.current?.condition?.text
        val image = view.findViewById<ImageView>(R.id.imageWeather)
        Picasso.with(image.context).load("https:"+weather.current?.condition?.icon).into(image)
        view.findViewById<TextView>(R.id.condition_title).text = condition

        // Localisation
        view.findViewById<TextView>(R.id.textCity).text = weather.location?.name

        // Humidité
        val humidity = weather.current?.humidity.toString() + '%'
        view.findViewById<TextView>(R.id.humidity).text = humidity

        // Vent
        val wind = weather.current?.wind_kph.toString() + " km/h "
        val windDirection =  weather.current?.wind_dir
        view.findViewById<TextView>(R.id.wind).text = wind
        view.findViewById<TextView>(R.id.wind_direction).text = windDirection

        // Température
        val temp = weather.current?.temp_c.toString() + " °C"
        view.findViewById<TextView>(R.id.temp).text = temp

        // Local time
        val localtime = weather.location?.localtime
        view.findViewById<TextView>(R.id.localtime).text = localtime

        // Back Navigation
        view.findViewById<ImageButton>(R.id.back_button).setOnClickListener{
            it.findNavController().navigate(R.id.action_navigation_details_to_navigation_search)
        }
    }
}