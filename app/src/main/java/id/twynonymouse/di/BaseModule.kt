package id.twynonymouse.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import id.twynonymouse.core.api.BaseApi
import id.twynonymouse.core.interactor.LoginInteract
import id.twynonymouse.core.interactor.UserInteract
import id.twynonymouse.core.utils.SharedPref
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class BaseModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    fun provideSharedPref(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    fun providePreferencesEditor(pref: SharedPreferences) = pref.edit()

    @Singleton
    @Provides
    fun provideMySharedPreferences(
        pref: SharedPreferences,
        editor: SharedPreferences.Editor
    ) = SharedPref(pref, editor)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://private-b50edc-funn.apiary-mock.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) = retrofit.create(BaseApi::class.java)

    @Provides
    @Singleton
    fun provideUserInteract(baseApi: BaseApi, pref: SharedPref) = UserInteract(baseApi, pref)

    @Provides
    @Singleton
    fun provideLoginInteract(baseApi: BaseApi) = LoginInteract(baseApi)
}