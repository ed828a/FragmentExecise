package com.dew.edward.fragmentexecise.fragments


import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.dew.edward.fragmentexecise.R
import com.dew.edward.fragmentexecise.modules.GlideApp
import kotlinx.android.synthetic.main.fragment_three.*

class ThreeFragment : Fragment() {
    val image = "http://webs.dogs.net.au/arawn/images/album_6083_991.jpg"

    private fun loadImage(){
        progressThirdFragment.visibility = View.VISIBLE
        GlideApp.with(activity!!).asBitmap().load(Uri.parse(image))
                .into(object : BitmapImageViewTarget(imageThirdFragment){
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        super.onResourceReady(resource, transition)
                        progressThirdFragment.visibility = View.INVISIBLE
                    }
                })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadImage()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three, container, false)
    }


    companion object {

        @JvmStatic
        fun newInstance() = ThreeFragment()

    }
}
