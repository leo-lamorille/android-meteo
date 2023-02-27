package com.llamorille.androidmeteo.detail

import android.os.Bundle
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
import com.llamorille.androidmeteo.model.ForecastDay
import com.llamorille.androidmeteo.model.Location
import com.squareup.picasso.Picasso

class DetailFragment: Fragment() {
    val args: DetailFragmentArgs by navArgs()
    var index: Int = 0;
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
        val loc: Location? = args.weather.location
        val weather = args.weather.forecast?.forecastday

        setData(view, loc, weather)

        // Back Navigation
        view.findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_details_to_navigation_search)
        }

        view.findViewById<Button>(R.id.nextDay).setOnClickListener {
            index = (index+1) % 7
            setData(view, loc, weather)
        }
    }

    fun setData(view: View, loc: Location?, weather: List<ForecastDay>?) {
        // Conditions
        val condition = weather?.elementAt(index)?.day?.condition?.text
        val image = view.findViewById<ImageView>(R.id.imageWeather)
        Picasso.with(image.context).load("https:" + weather?.elementAt(index)?.day?.condition?.icon)
            .into(image)
        view.findViewById<TextView>(R.id.condition_title).text = condition

        // Localisation
        view.findViewById<TextView>(R.id.textCity).text = loc?.name
        view.findViewById<TextView>(R.id.textRegion).text = " (" + loc?.region + ")"

        // Humidité
        val humidity = weather?.elementAt(index)?.day?.avghumidity.toString() + '%'
        view.findViewById<TextView>(R.id.humidity).text = humidity

        // Vent
        val wind = weather?.elementAt(index)?.day?.maxwind_kph.toString() + " km/h "
        view.findViewById<TextView>(R.id.wind).text = wind

        // Température
        val temp = weather?.elementAt(index)?.day?.avgtemp_c.toString() + " °C"
        view.findViewById<TextView>(R.id.temp).text = temp

        // Local time
        val localtime = weather?.elementAt(index)?.date
        view.findViewById<TextView>(R.id.localtime).text = localtime
    }
}