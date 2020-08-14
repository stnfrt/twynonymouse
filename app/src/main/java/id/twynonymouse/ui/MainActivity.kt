package id.twynonymouse.ui

import android.os.Bundle
import co.zsmb.rainbowcake.navigation.SimpleNavActivity
import dagger.android.AndroidInjection
import id.twynonymouse.R
import id.twynonymouse.core.utils.SharedPref
import id.twynonymouse.ui.auth.LoginFragment
import id.twynonymouse.ui.home.HomeFragment
import javax.inject.Inject

class MainActivity : SimpleNavActivity() {

    @Inject
    lateinit var pref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        navigator.add(LoginFragment())
    }
}