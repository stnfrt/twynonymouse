package id.twynonymouse.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import id.twynonymouse.BuildConfig
import id.twynonymouse.core.api.BaseApi
import id.twynonymouse.core.interactor.LoginInteract
import id.twynonymouse.core.interactor.UserInteract
import id.twynonymouse.core.utils.SharedPref
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor
import java.util.concurrent.TimeUnit
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
    fun provideRetrofit(): Retrofit {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val consumer =
            OkHttpOAuthConsumer(BuildConfig.TWITTER_CONSUMER, BuildConfig.TWITTER_CONSUMER_SECRET)
        consumer.setTokenWithSecret(BuildConfig.TWITTER_TOKEN, BuildConfig.TWITTER_TOKEN_SECRET)

        val client = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(SigningInterceptor(consumer))
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.TWITTER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

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