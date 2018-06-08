package com.dew.edward.fragmentexecise

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.dew.edward.fragmentexecise.adapters.FragmentViewPagerAdapter
import com.dew.edward.fragmentexecise.fragments.FirstFragment
import com.dew.edward.fragmentexecise.fragments.SecondFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val drawerToogle by lazy {
        ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = FirstFragment.newInstance()
        replaceFragment(fragment)

        navigationView.setNavigationItemSelectedListener {
            selectDrawerItem(it)
            true
        }

        setSupportActionBar(toolbar)
        drawerLayout.addDrawerListener(drawerToogle)

        viewPager.adapter = FragmentViewPagerAdapter(supportFragmentManager)
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

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun selectDrawerItem(item: MenuItem){

       /* val fragmentClass = */when (item.itemId) {
            R.id.firstFragment -> viewPager.currentItem = 0
            R.id.secondFragment -> viewPager.currentItem = 1
            else -> viewPager.currentItem = 0


//            R.id.firstFragment -> FirstFragment::class.java
//            R.id.secondFragment -> SecondFragment::class.java
//            else -> FirstFragment::class.java
        }

//        replaceFragment(fragmentClass.newInstance())
        drawerLayout.closeDrawer(GravityCompat.START)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

//        Log.d("Options", "onOptionsItemSelected was hit")
//        return if (drawerToogle.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)
//
        return when(item?.itemId){
            R.id.firstFragment ->{
                replaceFragment(FirstFragment.newInstance())
                true
            }

            R.id.secondFragment -> {
                replaceFragment(SecondFragment.newInstance())
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fragment_menu, menu)

        return true
    }
}
