package davidbaena.com.kotlinbook.app

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import davidbaena.com.kotlinbook.R.layout.item_forecast
import davidbaena.com.kotlinbook.domain.model.ForecastModel
import davidbaena.com.kotlinbook.domain.model.ForecastModelList
import davidbaena.com.kotlinbook.extensions.ctx
import davidbaena.com.kotlinbook.extensions.toDateString
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastListAdapter(val weekForecast: ForecastModelList,
                          val itemClick: (ForecastModel) -> Unit)
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(item_forecast, parent, false)
        return ForecastListAdapter.ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(weekForecast[position]) {
            holder.bindForecast(weekForecast[position])
        }
    }

    override fun getItemCount(): Int {
        return weekForecast.size
    }

    class ViewHolder(val view: View, val itemClick: (ForecastModel) -> Unit)
        : RecyclerView.ViewHolder(view) {

        val iconVh = view.icon_image_view!!
        val dateVh = view.date_text_view!!
        val descriptionVh = view.description_text_view!!
        val maxTemperatureVh = view.max_temperature_text_view!!
        val minTemperatureVh = view.min_temperature_text_view!!

        fun bindForecast(forecast: ForecastModel) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(iconVh)
                dateVh.text = date.toDateString()
                descriptionVh.text = description
                maxTemperatureVh.text = "$high"
                minTemperatureVh.text = "$low"
                view.setOnClickListener { itemClick(this) }
            }
        }
    }
}
