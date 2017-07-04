package davidbaena.com.kotlinbook.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import davidbaena.com.kotlinbook.R
import davidbaena.com.kotlinbook.domain.commands.RequestForecastCommand
import davidbaena.com.kotlinbook.domain.model.ForecastModelList
import davidbaena.com.kotlinbook.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar: Toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    val zipCode: Long by DelegatesExt.preference(this, SettingsActivity.ZIP_CODE,
            SettingsActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecastList.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecastList)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() {
        async(UI) {
            val result = bg { RequestForecastCommand(zipCode).execute() }
            updateUI(result.await())
        }
    }

    private fun updateUI(weeklyForecast: ForecastModelList) {
        val adapter = ForecastListAdapter(weeklyForecast) {
            startActivity<DetailActivity>(DetailActivity.ID to it.id,
                    DetailActivity.CITY_NAME to weeklyForecast.city)
        }
        forecastList.adapter = adapter
        toolbarTitle = "${weeklyForecast.city} (${weeklyForecast.country})"
    }
}
