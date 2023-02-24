package com.llamorille.androidmeteo.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
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

        val image = view.findViewById<ImageView>(R.id.imageWeather)
        Picasso.with(image.context).load("https:"+weather.current?.condition?.icon).into(image)
    }
}