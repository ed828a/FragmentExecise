package com.dew.edward.fragmentexecise.fragments


import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dew.edward.fragmentexecise.R
import com.dew.edward.fragmentexecise.VideoUriViewModel
import kotlinx.android.synthetic.main.fragment_video_view.*


private const val VIDEO_URI = "video Uri"

class VideoViewFragment : Fragment() {
    private val videUriViewModel by lazy {
        ViewModelProviders.of(activity!!).get(VideoUriViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        if (videUriViewModel.videoUri != null) {
            videoView.setVideoURI(videUriViewModel.videoUri )
            videoView.start()
        }
    }

    override fun onPause() {
        videoView.pause()
        super.onPause()
    }

    override fun onStop() {
        videoView.stopPlayback()
        super.onStop()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_view, container, false)
    }

    companion object {
        private val TAG = VideoIntentFragment::class.qualifiedName
        // the parameters in newInstance are for Activity to fire data to the Fragment
        @JvmStatic
        fun newInstance() =
                VideoViewFragment()
    }
}
