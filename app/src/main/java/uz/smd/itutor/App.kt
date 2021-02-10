package uz.smd.itutor

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.smd.itutor.data.prefs.LocalStorage

/**
 * Created by Siddikov Mukhriddin on 2/10/21
 */
@HiltAndroidApp
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        LocalStorage.init(this)
    }
}