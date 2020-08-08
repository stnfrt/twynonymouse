package id.twynonymouse

import android.os.Bundle
import co.zsmb.rainbowcake.navigation.SimpleNavActivity
import id.twynonymouse.home.HomeFragment

class MainActivity : SimpleNavActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.add(HomeFragment())
        }
    }
}