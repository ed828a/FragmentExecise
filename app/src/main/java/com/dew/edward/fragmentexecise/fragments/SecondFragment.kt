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
import kotlinx.android.synthetic.main.fragment_second.*


/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SecondFragment : Fragment() {
    val image = "http://transcoop.com.au/wp-content/uploads/maremmas-guard-dogs-1080x675.jpg"

    private fun loadImage(){
        progressSecondFragment.visibility = View.VISIBLE
        GlideApp.with(activity!!).asBitmap().load(Uri.parse(image))
                .into(object : BitmapImageViewTarget(imageSecondFragment){
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        super.onResourceReady(resource, transition)
                        progressSecondFragment.visibility = View.INVISIBLE
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
        return inflater.inflate(R.layout.fragment_second, container, false)
    }


    companion object {

        @JvmStatic
        fun newInstance() = SecondFragment()

    }
}
