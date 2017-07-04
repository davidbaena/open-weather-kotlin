package davidbaena.com.kotlinbook.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import davidbaena.com.kotlinbook.R
import davidbaena.com.kotlinbook.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber

class SettingsActivity : AppCompatActivity() {

    companion object {
        val ZIP_CODE = "zipCode"
        val DEFAULT_ZIP = 94043L
    }

    var zipCode: Long by DelegatesExt.preference(this, ZIP_CODE, DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        city_code_edit_text.setText(zipCode.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Timber.i("Zip code %s", city_code_edit_text.text.toString().toLong())
        zipCode = city_code_edit_text.text.toString().toLong()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }
}
