package com.dew.edward.fragmentexecise

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.dew.edward.fragmentexecise.fragments.VideoIntentFragment

class ShareDataActivity : AppCompatActivity() {

    private val TAG = ShareDataActivity::class.qualifiedName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_data)

        replaceFragment(VideoIntentFragment.newInstance())
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.shareDataFragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
    }
}
