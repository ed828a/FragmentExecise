package com.dew.edward.fragmentexecise.fragments

import android.app.Activity.RESULT_OK
import android.content.Context
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
import kotlinx.android.synthetic.main.fragment_video_intent.*

class VideoIntentFragment : Fragment() {

    private var listener: OnFragmentVideoUriListener? = null
    private var videoUri: Uri? = null
    private val VIDEO_APP_REQEST_CODE = 100

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_intent, container, false)
    }

    private fun callVideoApp(){
        val videoCaptureIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if (videoCaptureIntent.resolveActivity(activity?.packageManager) != null){
            startActivityForResult(videoCaptureIntent, VIDEO_APP_REQEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode){
            VIDEO_APP_REQEST_CODE -> {
                if (resultCode == RESULT_OK){
                    videoUri = data?.data

                }
            }

            else -> Log.e(TAG, "Unrecognized request code $requestCode")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonRecord.setOnClickListener{
            callVideoApp()
        }

        buttonPlay.setOnClickListener {
            listener?.onFragmentVideoUri(videoUri)
        }


    }

    // Fragment use this function to take Activity's processing code
    // this function or listener?.onFragmentVideoUri should be called in somewhere of this Fragment
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentVideoUri(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentVideoUriListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentVideoUriListener {
        // Activity uses this function to pass processing code to the Fragment
        // uri is provided by this Fragment, in other words,
        // Fragment use this function to send Uri to Activity
        fun onFragmentVideoUri(uri: Uri?)
    }

    companion object {
        val TAG = VideoIntentFragment::class.qualifiedName
        @JvmStatic
        fun newInstance() = VideoIntentFragment()
    }
}
