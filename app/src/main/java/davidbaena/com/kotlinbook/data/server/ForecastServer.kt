package davidbaena.com.kotlinbook.data.server

import davidbaena.com.kotlinbook.data.db.ForecastDb
import davidbaena.com.kotlinbook.domain.datasource.ForecastDataSource
import davidbaena.com.kotlinbook.domain.model.ForecastModel
import davidbaena.com.kotlinbook.domain.model.ForecastModelList

class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestDayForecast(id: Long): ForecastModel? = throw UnsupportedOperationException()

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastModelList? {
        val result = ForecastByZipCodeRequest(zipCode.toString()).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}