package com.magednan.cobbletaskapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.magednan.cobbletaskapplication.R
import com.magednan.cobbletaskapplication.adapter.WeatherAdapter
import com.magednan.cobbletaskapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //DataBinding
    private lateinit var binding: ActivityMainBinding
    //ViewModel
    val viewModel by viewModels<MainViewModel>()
    //RVAdapter
    lateinit var weatherAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DataBinding inflate
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //ToolBar inflate
        setSupportActionBar(binding.toolBar)
        binding.toolBar.setLogo(R.drawable.ic_location)

        getSupportActionBar()?.setTitle("Newyork");
        //Rv Inflate
        binding.mainRv.apply {
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            itemAnimator = null
        }
        //get Data From viewModel
        val latitude =  40.730610
        val longitude = -73.935242
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getData(latitude.toDouble(), longitude.toDouble())
            viewModel.listLiveData.observe(this@MainActivity, Observer {
                weatherAdapter = WeatherAdapter(this@MainActivity, it)
                binding.mainRv.adapter = weatherAdapter
                Log.d("TAG", "onCreateggg: " + it)
            })
        }
    }

    //OptionsMenu Inflate
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cities_menu, menu)
        return true
    }

    //Handling OptionsItem
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //set Data to cities
        return when (item.itemId) {
            R.id.newyork -> {
                setData("Newyork",40.730610,40.730610)
                return true
            }
            R.id.miami -> {

                setData("Miami",39.5092198,-84.7341196)
                return true
            }
            R.id.los_angeles -> {

                setData("Los Angeles",34.052235,-118.243683)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun setData(city:String,latitude:Double,longitude:Double){
        //set Data to chosen city
        getSupportActionBar()?.setTitle(city);
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getData(latitude.toDouble(), longitude.toDouble())
        }
    }
}