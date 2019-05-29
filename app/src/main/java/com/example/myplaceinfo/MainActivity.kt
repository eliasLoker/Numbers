package com.example.myplaceinfo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.my.ContainerFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toolbar: Toolbar
    private lateinit var drawer: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nd_activity_main_for_start_view)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)




        val fragment = supportFragmentManager.findFragmentById(R.id.container_for_fragments)
        if (fragment == null) {
                supportFragmentManager.beginTransaction()
                .add(R.id.container_for_fragments, ContainerFragment.newInstance())
                .commit()
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
