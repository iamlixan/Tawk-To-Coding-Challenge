package com.lixan.fajardo.tawkentranceexam.ext

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.lixan.fajardo.tawkentranceexam.GlideApp
import com.lixan.fajardo.tawkentranceexam.R
import timber.log.Timber

fun ImageView.loadUserAvatar(url: String?) {
    GlideApp.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_placeholder)
        .fallback(R.drawable.ic_placeholder)
        .skipMemoryCache(false)
//        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Timber.e("onLoadFailed Error $e")
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        })
        .into(this)

}