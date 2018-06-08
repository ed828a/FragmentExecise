package com.dew.edward.fragmentexecise

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.dew.edward.fragmentexecise.adapters.FragmentViewPagerAdapter
import kotlinx.android.synthetic.main.activity_bottom_nav.*


class BottomNavActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        bottomNavigationView.menu.findItem(bottomNavigationView.selectedItemId).isChecked = false
        bottomNavigationView.menu.getItem(position).isChecked = true
    }

    val drawerToogle by lazy {
        ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav)

        navigationView.setNavigationItemSelectedListener {

            selectItem(it)
        }

        setSupportActionBar(toolbar)
        drawerLayout.addDrawerListener(drawerToogle)

        viewPager.adapter = FragmentViewPagerAdapter(supportFragmentManager)
        viewPager.addOnPageChangeListener(this)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            selectItem(it)
        }
    }

    //configuration of synchronization between drawer toggle and drawer layout
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToogle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToogle.onConfigurationChanged(newConfig)
    }


    private fun selectItem(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.firstFragment -> viewPager.currentItem = 0
            R.id.secondFragment -> viewPager.currentItem = 1
            R.id.threeFragment -> viewPager.currentItem = 2
            else -> viewPager.currentItem = 0
        }

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        Log.d("Options", "onOptionsItemSelected was hit")
        return if (drawerToogle.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fragment_menu, menu)

        return true
    }
}
