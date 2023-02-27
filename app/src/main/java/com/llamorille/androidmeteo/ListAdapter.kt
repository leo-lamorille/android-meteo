package com.llamorille.androidmeteo

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.llamorille.androidmeteo.model.ForecastDay

class ListAdapter(): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var futureList: List<ForecastDay> = ArrayList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cityName: TextView
        var cityCountry: TextView
        var cityRegion: TextView
        var card: CardView

        init {
            cityName = itemView.findViewById(R.id.city_name)
            cityCountry = itemView.findViewById(R.id.city_country)
            cityRegion = itemView.findViewById(R.id.city_region)
            card = itemView.findViewById(R.id.card)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}