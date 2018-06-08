package com.dew.edward.fragmentexecise

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.dew.edward.fragmentexecise.fragments.FirstFragment
import com.dew.edward.fragmentexecise.fragments.SecondFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = FirstFragment.newInstance()
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
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
