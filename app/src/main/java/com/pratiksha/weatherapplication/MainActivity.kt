
package com.pratiksha.weatherapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.pratiksha.weatherapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val OPEN_WEATHER_MAP_BASE_URL = "https://api.openweathermap.org/data/2.5/"
const val OPEN_WEATHER_MAP_API_KEY = "477fc0ac2cb2b350df55e9549de32505"

class MainActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityMainBinding

    private lateinit var searchInput : EditText
    private lateinit var searchIcon : ImageView
    private lateinit var weatherImage : ImageView
    private lateinit var temperatureText : TextView
    private lateinit var locationText : TextView
    private lateinit var weatherConditionText : TextView
    private lateinit var city : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchInput = binding.searchText
        searchIcon = binding.searchIcon
        locationText = binding.locationText

        city = "chandrapur"



        searchInput.setOnKeyListener{ view, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER ) {
                // Perform your action here, e.g. search for the text in the EditText.
                println("it's working/////////////////////////////////////////////////////////////////////////////////")

                hideKeyboard()
                println("datafetch......----------------------------------------------------------------------------------")
                city = searchInput.text.toString()

                fetchWeatherData()


                // Return true to indicate that the action has been handled.
                true
            } else {
                // Return false to indicate that the action has not been handled.
                println("it's not working..........................................................................")
                false
            }
        }

        searchIcon.setOnClickListener {
            city = searchInput.text.toString()
            println("$city.................................................................")

            hideKeyboard()

            fetchWeatherData()
        }


        fetchWeatherData()

    }



    private fun fetchWeatherData(){
        weatherImage = binding.weatherImg
        temperatureText = binding.temperatureText
        locationText = binding.locationText
        weatherConditionText = binding.weatherConditionText

        val retrofit =  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(OPEN_WEATHER_MAP_BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val response = retrofit.getWeatherData(city, OPEN_WEATHER_MAP_API_KEY)
        response.enqueue(object : Callback<WeatherCity>{

            override fun onResponse(call: Call<WeatherCity>, response: Response<WeatherCity>) {
                val responseBody = response.body()

                if(response.isSuccessful && responseBody != null){
                    val kelvinTemperature = responseBody.main.temp
                    val celsiusTemperature = kelvinTemperature - 273.15
                    val weatherType = responseBody.weather[0].main
                    val city = responseBody.name

                    temperatureText.text = "${celsiusTemperature.toInt()}°C"
                    locationText.text = city
                    weatherConditionText.text = weatherType

                    setWeatherImage(weatherType)

                }
            }

            override fun onFailure(call: Call<WeatherCity>, t: Throwable) {

            }

        })



    }

   private fun setWeatherImage(  weatherType : String){

       val drawableResId =  when(weatherType.toLowerCase()){

           "rain" -> R.drawable.rain
           "sunny" -> R.drawable.sun
           "thunder" -> R.drawable.storm
           "haze" -> R.drawable.haze
           "clouds" -> R.drawable.cloudy

           else -> R.drawable.sun

       }

       weatherImage.setImageResource(drawableResId)


   }

    private fun hideKeyboard(){
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(searchInput.windowToken , 0)
    }




}