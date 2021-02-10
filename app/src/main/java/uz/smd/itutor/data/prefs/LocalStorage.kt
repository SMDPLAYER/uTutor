package uz.smd.itutor.data.prefs

import android.content.Context
import javax.inject.Inject

class LocalStorage @Inject constructor(context: Context) {

    companion object {
        @Volatile
        lateinit var instance: LocalStorage
        fun init(context: Context) {
            instance =
                LocalStorage(context)

        }
    }

    private var pref = context.getSharedPreferences("data", Context.MODE_PRIVATE)
    var name by StringPreference(pref)
}