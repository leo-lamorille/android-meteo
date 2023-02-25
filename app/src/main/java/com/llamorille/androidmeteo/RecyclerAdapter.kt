package com.llamorille.androidmeteo
import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.llamorille.androidmeteo.model.SearchResponse

class RecyclerAdapter(): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var searchList: List<SearchResponse> = ArrayList();

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
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d("POSITION", position.toString())
        viewHolder.cityName.text = searchList.elementAt(position).name
        viewHolder.cityCountry.text = searchList.elementAt(position).country
        if (searchList.elementAt(position).region != "") {
            viewHolder.cityRegion.text = " (" + searchList.elementAt(position).region + ")"
        }

        viewHolder.card.setOnClickListener {
            val city = searchList.elementAt(position)
            Log.d("TEST", city.name.toString())
            viewHolder.itemView.findViewById<AutoCompleteTextView>(R.id.searchAdress).setText(city.name.toString())
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addSearchList(searches: List<SearchResponse>) {
        searchList = searches
        Log.d("ADAPTER", searches.toString())
        notifyDataSetChanged()
        Log.d("ADAPTER TEST", searches.toString())
    }
}