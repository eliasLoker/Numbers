package com.example.myplaceinfo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myplaceinfo.container.ContainerFragment
import com.example.myplaceinfo.numberlist.NumberListFragment
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
        if (item.itemId == R.id.about) {
            val fragment = supportFragmentManager.findFragmentByTag("NUMBER_LIST")
            if (fragment == null) {
                supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container_for_fragments, NumberListFragment.newInstance(), "NUMBER_LIST")
                    .commit()
            }

        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
