package id.twynonymouse.ui

import android.os.Bundle
import co.zsmb.rainbowcake.navigation.SimpleNavActivity
import id.twynonymouse.R
import id.twynonymouse.ui.auth.LoginFragment
import id.twynonymouse.ui.home.HomeFragment

class MainActivity : SimpleNavActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.add(LoginFragment())
        }
    }
}