package davidbaena.com.kotlinbook.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.squareup.picasso.Picasso
import davidbaena.com.kotlinbook.R
import davidbaena.com.kotlinbook.domain.commands.RequestDayForecastCommand
import davidbaena.com.kotlinbook.domain.model.ForecastModel
import davidbaena.com.kotlinbook.extensions.color
import davidbaena.com.kotlinbook.extensions.toDateString
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor
import java.text.DateFormat


class DetailActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar: Toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar()

        toolbarTitle = intent.getStringExtra(CITY_NAME)
        enableHomeAsUp { onBackPressed() }

        title = intent.getStringExtra(CITY_NAME)

        val ref = asReference()
        val id = intent.getLongExtra(ID, -1)

        async(UI) {
            val result = bg { RequestDayForecastCommand(id).execute() }
            ref().bindForecast(result.await())
        }
    }

    private fun bindForecast(forecastModel: ForecastModel) = with(forecastModel) {
        Picasso.with(ctx).load(iconUrl).into(icon)
        supportActionBar?.subtitle = date.toDateString(DateFormat.FULL)
        toolbar.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}"
        it.second.textColor = ctx.color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}

