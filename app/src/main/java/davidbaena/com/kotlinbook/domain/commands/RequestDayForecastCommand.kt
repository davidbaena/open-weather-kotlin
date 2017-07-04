package davidbaena.com.kotlinbook.domain.commands

import davidbaena.com.kotlinbook.domain.datasource.ForecastProvider
import davidbaena.com.kotlinbook.domain.model.ForecastModel

class RequestDayForecastCommand(val id: Long, val forecastProvider: ForecastProvider = ForecastProvider())
    : Command<ForecastModel> {
    override fun execute(): ForecastModel = forecastProvider.requestForecast(id)
}