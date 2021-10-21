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
        //get Data From SharedPreferences
        val prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        val name = prefs.getString("city", "Newyork") //"No name defined" is the default value.
        val latitude = prefs.getFloat("Latitude", 40.730610F) //"No name defined" is the default value.
        val longitude = prefs.getFloat("Longitude", -73.935242F) //"No name defined" is the default value.
        getSupportActionBar()?.setTitle(name);
        //Rv Inflate
        binding.mainRv.apply {
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            itemAnimator = null
        }
        //get Data From API
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
        //set Data to SharedPreferences
        val editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
        val prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)

        return when (item.itemId) {
            R.id.newyork -> {
                editor.putString("city", "newyork").putFloat("Latitude", 40.730610F)
                        .putFloat("Longitude", 40.730610F).apply()
                val name = prefs.getString("city", "Nework") //"No name defined" is the default value.
                getSupportActionBar()?.setTitle(name);
                val latitude = prefs.getFloat("Latitude", 40.730610F) //"No name defined" is the default value.
                val longitude = prefs.getFloat("Longitude", -73.935242F) //"No name defined" is the defaul
                lifecycleScope.launch(Dispatchers.Main) {
                    viewModel.getData(latitude.toDouble(), longitude.toDouble())
                }
                return true
            }
            R.id.los_angeles -> {
                editor.putString("city", "los Angeles").putFloat("Latitude", 34.052235F)
                        .putFloat("Longitude", -118.243683F).apply()
                val name = prefs.getString("city", "Nework") //"No name defined" is the default value.
                getSupportActionBar()?.setTitle(name);
                val latitude = prefs.getFloat("Latitude", 40.730610F) //"No name defined" is the default value.
                val longitude = prefs.getFloat("Latitude", -73.935242F) //"No name defined" is the defaul
                lifecycleScope.launch(Dispatchers.Main) {
                    viewModel.getData(latitude.toDouble(), longitude.toDouble())
                }
                return true
            }
            R.id.miami -> {
                editor.putString("city", "miami").putFloat("Latitude", 39.5092198F)
                        .putFloat("Longitude", -84.7341196F).apply()
                val name = prefs.getString("city", "Nework") //"No name defined" is the default value.
                getSupportActionBar()?.setTitle(name);
                val latitude = prefs.getFloat("Latitude", 40.730610F) //"No name defined" is the default value.
                val longitude = prefs.getFloat("Longitude", -73.935242F) //"No name defined" is the defaul
                lifecycleScope.launch(Dispatchers.Main) {
                    viewModel.getData(latitude.toDouble(), longitude.toDouble())
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}