package davidbaena.com.kotlinbook.data.db

import davidbaena.com.kotlinbook.domain.model.ForecastModel
import davidbaena.com.kotlinbook.domain.model.ForecastModelList


class DbDataMapper {

    fun convertFromDomain(forecastList: ForecastModelList): CityForecast = with(forecastList) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: ForecastModel): DayForecast = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }

    fun convertToDomain(cityForecast: CityForecast): ForecastModelList = with(cityForecast) {
        val daily: List<ForecastModel> = dailyForecast.map { dayForecast -> convertDayToDomain(dayForecast) }
        ForecastModelList(_id, city, country, daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast): ForecastModel = with(dayForecast) {
        ForecastModel(_id, date, description, high, low, iconUrl)
    }
}