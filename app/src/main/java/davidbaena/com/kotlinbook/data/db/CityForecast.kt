package davidbaena.com.kotlinbook.data.db

class CityForecast(val map: MutableMap<String, Any?>,
                   val dailyForecast: List<DayForecast>) {
    var _id: Long by map
    var city: String by map
    var country: String by map

    constructor(id: Long, city: String, country: String,
                dailyForecast: List<DayForecast>)
            : this(HashMap(), dailyForecast) {
        this._id = id
        this.city = city
        this.country = country
    }

    /**
    CityForecast
    +-----+----------+---------+
    | _id |   city   | country |
    +-----+----------+---------+
    |   1 | London   | UK      |
    |   2 | Valencia | ES      |
    |   3 | Madrid   | ES      |
    +-----+----------+---------+
     */
    object CityForecastTable {
        val NAME = "CityForecast"
        val ID = "_id"
        val CITY = "city"
        val COUNTRY = "country"
    }
}

