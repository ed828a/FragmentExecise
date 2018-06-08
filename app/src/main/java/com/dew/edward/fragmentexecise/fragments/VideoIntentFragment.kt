package com.dew.edward.fragmentexecise.fragments

import android.app.Activity.RESULT_OK
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dew.edward.fragmentexecise.R
import com.dew.edward.fragmentexecise.VideoUriViewModel
import kotlinx.android.synthetic.main.fragment_video_intent.*

class VideoIntentFragment : Fragment() {

    private var videoUri: Uri? = null
    private val VIDEO_APP_REQEST_CODE = 100
    private val videUriViewModel by lazy {
        ViewModelProviders.of(activity!!).get(VideoUriViewModel::class.java)
    }

    private fun startVideoViewFragment() {
        val videoViewFragment = VideoViewFragment.newInstance()
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.shareDataFragmentContainer, videoViewFragment)
                ?.addToBackStack(null)
                ?.commit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_intent, container, false)
    }

    private fun callVideoApp() {
        val videoCaptureIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if (videoCaptureIntent.resolveActivity(activity?.packageManager) != null) {
            startActivityForResult(videoCaptureIntent, VIDEO_APP_REQEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            VIDEO_APP_REQEST_CODE -> {
                if (resultCode == RESULT_OK) {
                    videoUri = data?.data
                }
            }
            else -> Log.e(TAG, "Unrecognized request code $requestCode")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonRecord.setOnClickListener {
            callVideoApp()
        }

        buttonPlay.setOnClickListener {
            if (videoUri != null)
                videUriViewModel.videoUri = videoUri
            startVideoViewFragment()
        }
    }

    companion object {
        val TAG = VideoIntentFragment::class.qualifiedName
        @JvmStatic
        fun newInstance() = VideoIntentFragment()
    }
}
