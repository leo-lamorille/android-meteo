package com.llamorille.androidmeteo.future

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.llamorille.androidmeteo.R
import com.llamorille.androidmeteo.search.SearchViewModel

class FutureFragment: Fragment() {
    val args: FutureFragmentArgs by navArgs()
    private lateinit var searchViewModel: SearchViewModel;


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val location = args.location
        Log.d("FUTURE", location.toString())
        // Inflate the layout for this fragment
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        searchViewModel.fetchFutureWeather(location.name.toString())

        searchViewModel.futureWeather.observe(viewLifecycleOwner) {
            future -> Log.d("FUTURE CALL", future.toString())
        }
        return inflater.inflate(R.layout.fragment_future, container, false)
    }
}