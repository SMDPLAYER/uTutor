package uz.smd.itutor.data.prefs

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Siddiqov Muxriddin on 10.02.2021
 */

@Singleton
class LocalStorage @Inject constructor(@ApplicationContext context: Context) {

    private var pref = context.getSharedPreferences("data", Context.MODE_PRIVATE)

    var name by StringPreference(pref)
    var storedVerificationId by StringPreference(pref)
    var resendToken by StringPreference(pref)
    var isAuthed by BooleanPreference(pref)
    var user by StringPreference(pref)
}