package id.twynonymouse.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import id.twynonymouse.core.api.UserApi
import id.twynonymouse.core.interactor.UserInteract
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class BaseModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit) = retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideUserInteractor(userApi: UserApi) = UserInteract(userApi)
}