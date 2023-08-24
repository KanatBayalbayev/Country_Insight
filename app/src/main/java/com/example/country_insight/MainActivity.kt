package com.example.country_insight

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.country_insight.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val capitalTemplate = applicationContext.resources.getString(R.string.capital_template)
        val subregionTemplate = applicationContext.resources.getString(R.string.subregion_template)
        val populationTemplate =
            applicationContext.resources.getString(R.string.population_template)
        viewModel.getData().observe(this) {
            for (country in it) {
                val name = country.name
                Picasso.get().load(country.flags.png).into(binding.imageViewFlag)
                binding.textViewCountryName.text = name.common
                binding.textViewCapital.text = String.format(capitalTemplate, country.capital[0])
                binding.textViewSubregion.text = String.format(subregionTemplate, country.subregion)
                binding.textViewPopulation.text =
                    String.format(populationTemplate, country.population)
                Log.d("Country", country.toString())
            }
            Log.d("Country", it.toString())

        }

        viewModel.getIsLoading().observe(this)  {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.getIsError().observe(this)  {
            if (it) {
                Toast.makeText(this,"Ошибка загрузки!", Toast.LENGTH_SHORT).show()
            }
        }



        binding.buttonToGetData.setOnClickListener {
            val countryName = binding.inputFromUser.text.toString().trim()
            Log.d("MainActivity", countryName)
            viewModel.loadData(countryName)
        }


    }


}