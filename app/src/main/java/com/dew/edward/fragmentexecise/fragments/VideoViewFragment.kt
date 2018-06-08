package com.dew.edward.fragmentexecise.fragments


import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dew.edward.fragmentexecise.R
import kotlinx.android.synthetic.main.fragment_video_view.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val VIDEO_URI = "video Uri"

/**
 * A simple [Fragment] subclass.
 * Use the [VideoViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class VideoViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var videoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoUri = it.getParcelable(VIDEO_URI)
        }
    }

    override fun onStart() {
        super.onStart()
        videoView.setVideoURI(videoUri)
        videoView.start()
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
        fun newInstance(uri: Uri) =
                VideoViewFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(VIDEO_URI, uri)
                    }
                }
    }
}
