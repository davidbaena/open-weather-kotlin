package davidbaena.com.kotlinbook.data.server

import davidbaena.com.kotlinbook.domain.model.ForecastModel
import davidbaena.com.kotlinbook.domain.model.ForecastModelList
import java.util.*
import java.util.concurrent.TimeUnit

class ServerDataMapper {

    fun convertToDomain(zipCode: Long, forecastNetwork: ForecastNetworkResult) = with(forecastNetwork) {
        ForecastModelList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<ForecastNetwork>): List<ForecastModel> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecastNetwork: ForecastNetwork) = with(forecastNetwork) {
        ForecastModel(-1, dt, weather[0].description, temp.max.toInt(), temp.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}