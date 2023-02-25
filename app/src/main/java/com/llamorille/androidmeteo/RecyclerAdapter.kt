package com.llamorille.androidmeteo
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.llamorille.androidmeteo.model.SearchResponse

class RecyclerAdapter(): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var searchList: List<SearchResponse> = ArrayList();
    private lateinit var input: AutoCompleteTextView
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
        viewHolder.cityName.text = searchList.elementAt(position).name
        viewHolder.cityCountry.text = searchList.elementAt(position).country
        if (searchList.elementAt(position).region != "") {
            viewHolder.cityRegion.text = " (" + searchList.elementAt(position).region + ")"
        }

        viewHolder.card.setOnClickListener {
            input.setText(searchList.elementAt(position).name + " - " + searchList.elementAt(position).country)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addSearchList(searches: List<SearchResponse>) {
        searchList = searches
        notifyDataSetChanged()
    }

    fun addInput(inputAutoComplete: AutoCompleteTextView) {
        input = inputAutoComplete
    }
}