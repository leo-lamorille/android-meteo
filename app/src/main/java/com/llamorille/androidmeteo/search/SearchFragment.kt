package com.llamorille.androidmeteo.search

import android.os.Bundle
import android.util.Log
import android.view.InputDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.llamorille.androidmeteo.R
import com.llamorille.androidmeteo.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding;

    private lateinit var searchViewModel: SearchViewModel;

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
        val button = view.findViewById<Button>(R.id.buttonSearch);

        button.setOnClickListener{
            val city: String = view.findViewById<EditText>(R.id.searchAdress).text.toString();
            searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
            searchViewModel.weather.observe(viewLifecycleOwner) {weather ->

            }
            searchViewModel.fetchWeatherByCity(city)
            it.findNavController().navigate(R.id.action_navigation_search_to_navigation_details)
        }

    }
}