package com.llamorille.androidmeteo.search

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
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.llamorille.androidmeteo.R
import com.llamorille.androidmeteo.RecyclerAdapter

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
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager=layoutManager
        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter

        val button = view.findViewById<Button>(R.id.buttonSearch)
        val input = view.findViewById<AutoCompleteTextView>(R.id.searchAdress)
        adapter.addInput(input)

        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        button.setOnClickListener{
            val city: String = input.text.toString();
            val bundle = Bundle()
            searchViewModel.weather.observe(viewLifecycleOwner) {weather ->
                bundle.putSerializable("MyData", weather)
                val action = SearchFragmentDirections.actionNavigationSearchToNavigationDetails(weather)
                it.findNavController().navigate(action)
            }
            searchViewModel.fetchWeatherByCity(city)

        }


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
                Log.d("AFTER", p0.toString())
                if(!p0.isNullOrEmpty()) {
                    searchViewModel.searchCity(p0.toString())
                }
            }
        })
    }
}