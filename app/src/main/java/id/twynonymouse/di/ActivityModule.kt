package id.twynonymouse.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.twynonymouse.ui.MainActivity

@Module
abstract class ActivityModule{

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}