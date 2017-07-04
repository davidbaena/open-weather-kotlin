package davidbaena.com.kotlinbook.domain.commands

import davidbaena.com.kotlinbook.domain.datasource.ForecastProvider
import davidbaena.com.kotlinbook.domain.model.ForecastModelList

class RequestForecastCommand(val zipCode: Long, val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastModelList> {

    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastModelList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }

}