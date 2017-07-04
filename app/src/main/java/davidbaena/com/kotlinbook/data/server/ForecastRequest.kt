package davidbaena.com.kotlinbook.data.server

import android.util.Log
import com.google.gson.Gson
import davidbaena.com.kotlinbook.BuildConfig
import java.net.URL

class ForecastByZipCodeRequest(val zipCode: String) {

    companion object {
        private val APP_ID = BuildConfig.API_KEY
        private val URL = "http://api.openweathermap.org/data/2.5/" + "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    fun execute(): ForecastNetworkResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        Log.d("URL", COMPLETE_URL + zipCode)
        return Gson().fromJson(forecastJsonStr, ForecastNetworkResult::class.java)
    }
}