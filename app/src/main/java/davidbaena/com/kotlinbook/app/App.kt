package davidbaena.com.kotlinbook.app

import android.app.Application
import davidbaena.com.kotlinbook.BuildConfig
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}