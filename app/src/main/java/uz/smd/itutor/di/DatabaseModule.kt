package uz.smd.itutor.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import uz.smd.itutor.data.prefs.LocalStorage
import javax.inject.Singleton

/**
 * Created by Siddikov Mukhriddin on 2/10/21
 */
@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun prefs():LocalStorage=LocalStorage.instance

}