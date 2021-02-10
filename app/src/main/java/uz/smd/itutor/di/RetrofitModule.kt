package uz.smd.itutor.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {
//    @Provides
//    @Singleton
//    fun getRetrofit(): Retrofit = Retrofit.Builder()
//        .baseUrl("https://jsonplaceholder.typicode.com/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
}