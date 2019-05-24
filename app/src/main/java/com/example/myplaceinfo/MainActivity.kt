package com.example.myplaceinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myplaceinfo.showip.ShowIpFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ShowIpFragment.newInstance())
                .commit()
        }
    }
}
