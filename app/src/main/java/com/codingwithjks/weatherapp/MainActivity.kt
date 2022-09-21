package com.codingwithjks.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.codingwithjks.weatherapp.ViewModel.WeatherViewModel
import com.codingwithjks.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.math.roundToInt

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        intent.getStringExtra("city_extras")?.let { weatherViewModel.setSearchQuery(it) }
        lifecycleScope.launchWhenStarted {
            weatherViewModel.weatherResponse.collect { response ->

                when (response.weather[0].description) {
                    "clear sky", "mist" -> {
                        Glide.with(this@MainActivity)
                            .load(R.drawable.clouds)
                            .into(binding.image)
                    }
                    "haze", "overcast clouds" -> {
                        Glide.with(this@MainActivity)
                            .load(R.drawable.haze)
                            .into(binding.image)
                    }
                    else -> {
                        Glide.with(this@MainActivity)
                            .load(R.drawable.rain)
                            .into(binding.image)
                    }
                }

                binding.description.text = response.weather[0].description
                binding.name.text = response.name
                binding.degree.text = response.wind.deg.toString()
                binding.speed.text = response.wind.speed.toString()
                binding.temp.text = (((response.main.temp - 273) * 100.0).roundToInt() / 100.0).toString()
                binding.humidity.text = response.main.humidity.toString()

            }
        }
    }
}
