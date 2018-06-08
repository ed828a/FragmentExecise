package com.dew.edward.fragmentexecise

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.dew.edward.fragmentexecise.fragments.VideoIntentFragment
import com.dew.edward.fragmentexecise.fragments.VideoViewFragment

class ShareDataActivity : AppCompatActivity(), VideoIntentFragment.OnFragmentVideoUriListener {

    private val TAG = ShareDataActivity::class.qualifiedName

    override fun onFragmentVideoUri(uri: Uri?) {
        Log.e(TAG, "Video Uri: $uri")
        if (uri != null) {
            // this is the place where activity fire data to Fragment.
            val videoViewFragment = VideoViewFragment.newInstance(uri)
            replaceFragment(videoViewFragment)
        }
    }

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
