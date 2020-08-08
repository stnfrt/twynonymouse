package id.twynonymouse.di

import android.app.Application
import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import co.zsmb.rainbowcake.dagger.RainbowCakeModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import id.twynonymouse.base.BaseApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        BaseModule::class,
        RainbowCakeModule::class,
        ViewModelModule::class
    ]
)
interface BaseComponent : RainbowCakeComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): BaseComponent
    }

    fun inject(app: BaseApp)
}