package davidbaena.com.kotlinbook.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import davidbaena.com.kotlinbook.app.App
import org.jetbrains.anko.db.*


class ForecastDbHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(ctx,
        DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(CityForecast.CityForecastTable.NAME,
                true,
                CityForecast.CityForecastTable.ID to INTEGER + PRIMARY_KEY,
                CityForecast.CityForecastTable.CITY to TEXT,
                CityForecast.CityForecastTable.COUNTRY to TEXT)

        db.createTable(DayForecast.DayForecastTable.NAME,
                true,
                DayForecast.DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DayForecast.DayForecastTable.DATE to INTEGER,
                DayForecast.DayForecastTable.DESCRIPTION to TEXT,
                DayForecast.DayForecastTable.HIGH to INTEGER,
                DayForecast.DayForecastTable.LOW to INTEGER,
                DayForecast.DayForecastTable.ICON_URL to TEXT,
                DayForecast.DayForecastTable.CITY_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(CityForecast.CityForecastTable.NAME, true)
        db.dropTable(DayForecast.DayForecastTable.NAME, true)
        onCreate(db)
    }

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance by lazy {
            ForecastDbHelper()
        }
    }

}