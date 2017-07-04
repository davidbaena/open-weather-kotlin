package davidbaena.com.kotlinbook.data.db

class DayForecast(var map: MutableMap<String, Any?>) {

    var _id: Long by map
    var date: Long by map
    var description: String by map
    var high: Int by map
    var low: Int by map
    var iconUrl: String by map
    var cityId: Long by map

    constructor(date: Long, description: String, high: Int, low: Int, iconUrl: String, cityId: Long) : this(HashMap()) {
        this.date = date
        this.description = description
        this.high = high
        this.low = low
        this.iconUrl = iconUrl
        this.cityId = cityId
    }

    /**
    DayForecast
    +-----+-----------+---------------+-------+------+-----------------------------------------+--------+
    | _id |   date    |  description  | high  | low  |                 iconUrl                 | cityId |
    +-----+-----------+---------------+-------+------+-----------------------------------------+--------+
    |  11 | 12/2/2017 | sky is clear  | 11.31 | 4.21 | http://openweathermap.org/img/w/2d.png  |      2 |
    |  21 | 12/2/2017 | moderate rain |  25.2 | 23.2 | http://openweathermap.org/img/w/23d.png |      3 |
    |  31 | 12/2/2017 | light rain    | 20.12 | 15.5 | http://openweathermap.org/img/w/22d.png |      1 |
    +-----+-----------+---------------+-------+------+-----------------------------------------+--------+
     */
    object DayForecastTable {
        val NAME = "DayForecast"
        val ID = "_id"
        val DATE = "date"
        val DESCRIPTION = "description"
        val HIGH = "high"
        val LOW = "low"
        val ICON_URL = "iconUrl"
        val CITY_ID = "cityId"
    }
}