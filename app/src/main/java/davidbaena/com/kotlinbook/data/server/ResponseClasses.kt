package davidbaena.com.kotlinbook.data.server

/**
 *
http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7&APPID=15646a06818f61f7b8d7823ca833e1ce&q=28022
"city": {
"id": 3120030,
"name": "La Alameda de Osuna",
"coord": {
"lon": -3.5834,
"lat": 40.45
},
"country": "ES",
"population": 0
},
"cod": "200",
"message": 6.132998,
"cnt": 7,
"list": [{
"dt": 1493985600,
"temp": {
"day": 18.2,
"min": 11.31,
"max": 18.2,
"night": 11.31,
"eve": 17.93,
"morn": 18.2
},
"pressure": 950.68,
"humidity": 92,
"weather": [{
"id": 501,
"main": "Rain",
"description": "moderate rain",
"icon": "10d"
}],
"speed": 7.26,
"deg": 255,
"clouds": 100,
"rain": 6.87
]
 */
data class ForecastNetworkResult(val city: City, val list: List<ForecastNetwork>)

data class City(val id: Long, val name: String, val coord: Coordinates,
                val country: String, val population: Int)

data class Coordinates(val lon: Float, val lat: Float)
data class ForecastNetwork(val dt: Long, val temp: Temperature, val pressure: Float,
                           val humidity: Int, val weather: List<Weather>,
                           val speed: Float, val deg: Int, val clouds: Int,
                           val rain: Float)

data class Temperature(val day: Float, val min: Float, val max: Float,
                       val night: Float, val eve: Float, val morn: Float)

data class Weather(val id: Long, val main: String, val description: String,
                   val icon: String)
