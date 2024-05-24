# WeatherApplication
This Weather Application allows users to get real-time weather information for any city. The application fetches weather data from the OpenWeatherMap API and displays the temperature, weather condition, and relevant weather icon for the specified city.
# Features
* Search for the current weather of any city
* Display temperature in Celsius
* Show weather conditions with appropriate icons
* Easy-to-use interface with a search bar

# ScreenShots
![Screenshot 2024-05-23 190525](https://github.com/Pratiksha-Rajpurohit/WeatherApplication/assets/132194955/18a0e8d9-0d3d-4f9f-b776-f67baea34823)
![Screenshot 2024-05-23 190546](https://github.com/Pratiksha-Rajpurohit/WeatherApplication/assets/132194955/7951d339-c29e-42c4-9b79-f9fa8433bcc3)
![Screenshot 2024-05-23 190640](https://github.com/Pratiksha-Rajpurohit/WeatherApplication/assets/132194955/17fabff5-5579-4cbd-9f6e-ae035b1c9f47)
![Screenshot 2024-05-23 191049](https://github.com/Pratiksha-Rajpurohit/WeatherApplication/assets/132194955/bf0c27f0-55b2-4200-b735-f99de5018b16)


  
# Technologies Used
* Kotlin
* Retrofit for network calls
* Gson for JSON parsing
* OpenWeatherMap API
* Android View Binding
# Getting Started
### Prerequisites
* Android Studio
* OpenWeatherMap API key
### Installation
1. Clone the repository:
 ``` bash
  git clone https://github.com/Pratiksha-Rajpurohit/WeatherApplication
   ```
  
  
2. Open the project in Android Studio:

    - Open Android Studio
    - Click on Open an existing project
    - Navigate to the cloned repository and open it
  
3. Add your OpenWeatherMap API key:
    - Open MainActivity.kt
    - Replace the placeholder API key with your own:

``` kotlin
const val OPEN_WEATHER_MAP_API_KEY = "your_api_key_here" 
```
4. Build and run the project:

    - Connect your Android device or start an emulator
    - Click on Run (green play button) in Android Studio
      
# Usage
1. Launch the app on your device.
2. Search for a city's weather:
    - Enter the city name in the search bar
    -  Press the Enter key or tap the search icon
4. View weather information:
     - The app will display the temperature, city name, and weather condition
     - The weather icon will update based on the condition (e.g., sunny, rain, clouds)
# Code Overview
### MainActivity
* onCreate(): Initializes the view and sets up listeners for the search bar and search icon.
* fetchWeatherData(): Makes a network call to the OpenWeatherMap API to fetch weather data for the specified city.
* setWeatherImage(): Updates the weather icon based on the weather condition.
* hideKeyboard(): Hides the soft keyboard after performing a search.
### API Interface
Defines the Retrofit interface for making network calls to the OpenWeatherMap API.

``` kotlin 
 
binterface ApiInterface {
    @GET("weather")
    fun getWeatherData(@Query("q") city: String, @Query("appid") apiKey: String): Call<WeatherCity>
}
```

### Models
Contains data classes for parsing JSON responses from the OpenWeatherMap API.

``` kotlin

data class WeatherCity(val weather: List<Weather>, val main: Main, val name: String) 

data class Weather(val main: String)

data class Main(val temp: Double) 
```


# Contact
For any questions or suggestions, please open an issue on GitHub or contact the repository owner at rajpurohitpratiksha123@gmail.com.
