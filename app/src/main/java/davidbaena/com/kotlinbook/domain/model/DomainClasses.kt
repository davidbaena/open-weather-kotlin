package davidbaena.com.kotlinbook.domain.model

data class ForecastModelList(val id: Long, val city: String, val country: String, val dailyForecast: List<ForecastModel>) {

    val size: Int
        get() = dailyForecast.size

    operator fun get(position: Int) = dailyForecast[position]
}

data class ForecastModel(val id: Long, val date: Long, val description: String, val high: Int, val low: Int,
                         val iconUrl: String)