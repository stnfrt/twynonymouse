package id.twynonymouse

import android.app.Activity
import co.zsmb.rainbowcake.config.Loggers
import co.zsmb.rainbowcake.config.rainbowCake
import co.zsmb.rainbowcake.dagger.RainbowCakeApplication
import co.zsmb.rainbowcake.timber.TIMBER
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import id.twynonymouse.di.BaseComponent
import id.twynonymouse.di.DaggerBaseComponent
import timber.log.Timber
import javax.inject.Inject

class BaseApp : RainbowCakeApplication(), HasActivityInjector {
    companion object {
        lateinit var instance: BaseApp
    }

    override lateinit var injector: BaseComponent

    override fun setupInjector() {
        injector = DaggerBaseComponent
            .builder()
            .application(this)
            .build()
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()

        rainbowCake {
            isDebug = BuildConfig.DEBUG
            logger = Loggers.TIMBER

            consumeExecuteExceptions = false
        }

        Timber.plant(Timber.DebugTree())
        injector.inject(this)
        instance = this
    }
}