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
import kotlinx.android.synthetic.main.fragment_first.*


/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FirstFragment : Fragment() {
    val image = "https://st2.depositphotos.com/1952119/5261/i/950/depositphotos_52616885-stock-photo-maremma-or-abruzzese-patrol-dog.jpg"

    private fun loadImage(){
        progressFirstFragment.visibility = View.VISIBLE
        GlideApp.with(activity!!).asBitmap().load(Uri.parse(image))
                .into(object : BitmapImageViewTarget(imageFirstFragment){
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        super.onResourceReady(resource, transition)
                        progressFirstFragment.visibility = View.INVISIBLE
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
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = FirstFragment()
    }
}
