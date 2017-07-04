package davidbaena.com.kotlinbook.data.db

import davidbaena.com.kotlinbook.domain.datasource.ForecastDataSource
import davidbaena.com.kotlinbook.domain.model.ForecastModel
import davidbaena.com.kotlinbook.domain.model.ForecastModelList
import davidbaena.com.kotlinbook.extensions.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import timber.log.Timber

class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.Companion.instance,
                 val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {


    override fun requestDayForecast(id: Long): ForecastModel? = forecastDbHelper.use {
        val forecast = select(DayForecast.DayForecastTable.NAME)
                .byId(id)
                .parseOpt { map ->
                    Timber.v("Day Forecast with id %s: %s", id, map)
                    DayForecast(HashMap(map))
                }
        forecast?.let { dataMapper.convertDayToDomain(it) }
    }

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastModelList? = forecastDbHelper.use {

        val dailyRequest = "${DayForecast.DayForecastTable.CITY_ID} = ? AND ${DayForecast.DayForecastTable.DATE} >= ?"

        val dailyForecast: List<DayForecast> = select(DayForecast.DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { map -> DayForecast(HashMap(map)) }

        val city: CityForecast? = select(CityForecast.CityForecastTable.NAME)
                .whereSimple("${CityForecast.CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { map -> CityForecast(HashMap(map), dailyForecast) }

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: ForecastModelList) = forecastDbHelper.use {
        clear(CityForecast.CityForecastTable.NAME)
        clear(DayForecast.DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecast.CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach { insert(DayForecast.DayForecastTable.NAME, *it.map.toVarargArray()) }
        }
    }
}
