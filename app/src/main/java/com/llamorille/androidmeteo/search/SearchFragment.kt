package com.llamorille.androidmeteo.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.llamorille.androidmeteo.R
import com.llamorille.androidmeteo.RecyclerAdapter
import retrofit2.HttpException

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel;
    private lateinit var adapter: RecyclerAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager=layoutManager
        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.buttonSearch)
        val input = view.findViewById<AutoCompleteTextView>(R.id.searchAdress)
        val errorMessage = view.findViewById<TextView>(R.id.errorMessage)
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        button.setOnClickListener {
            val city: String = input.text.toString();
            if (city.isEmpty()) {
                errorMessage.visibility = View.VISIBLE
                errorMessage.text = resources.getString(R.string.error_empty)
                return@setOnClickListener
            }
            searchViewModel.fetchWeatherByCity(city) {
                errorMessage.visibility = View.VISIBLE
                errorMessage.text = resources.getString(R.string.error_city_unknown, city);
            }

        }
        searchViewModel.weather.observe(viewLifecycleOwner) {weather ->
            val bundle = Bundle()
            bundle.putSerializable("MyData", weather)
            Log.d("WEATHER", weather.toString())
            val action = SearchFragmentDirections.actionNavigationSearchToNavigationDetails(weather)
            Log.d("ACTION", action.toString())
            view.findNavController().navigate(action)
        }

        adapter.addInput(input)

        input.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchViewModel.search.observe(viewLifecycleOwner) {
                        search -> adapter.addSearchList(search)
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("ON", p1.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
                if(!p0.isNullOrEmpty()) {
                    errorMessage.visibility = View.INVISIBLE
                    searchViewModel.searchCity(p0.toString())
                }
            }
        })
    }
}