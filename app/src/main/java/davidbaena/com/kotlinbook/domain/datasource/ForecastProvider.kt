package davidbaena.com.kotlinbook.domain.datasource

import davidbaena.com.kotlinbook.data.db.ForecastDb
import davidbaena.com.kotlinbook.data.server.ForecastServer
import davidbaena.com.kotlinbook.domain.model.ForecastModel
import davidbaena.com.kotlinbook.domain.model.ForecastModelList
import davidbaena.com.kotlinbook.extensions.firstResult

class ForecastProvider(val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES by lazy { listOf(ForecastDb(), ForecastServer()) }
    }

    fun requestForecast(id: Long): ForecastModel = requestToSources { forecastDataSource ->
        forecastDataSource.requestDayForecast(id)
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastModelList = requestToSources { forecastDataSource ->
        val res = forecastDataSource.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T
            = sources.firstResult { dataSource -> f(dataSource) }

    private fun todayTimeSpan(): Long = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}

