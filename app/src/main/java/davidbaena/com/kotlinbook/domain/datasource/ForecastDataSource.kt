package davidbaena.com.kotlinbook.domain.datasource

import davidbaena.com.kotlinbook.domain.model.ForecastModel
import davidbaena.com.kotlinbook.domain.model.ForecastModelList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastModelList?
    fun requestDayForecast(id: Long): ForecastModel?
}