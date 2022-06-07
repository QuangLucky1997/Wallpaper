package com.androiddev97.wallpaper2021.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.`interface`.CLickListener
import com.androiddev97.wallpaper2021.data.model.pexel.Photo
import com.androiddev97.wallpaper2021.data.model.popular.Popular
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.custom_random_pictures.view.*
import kotlinx.android.synthetic.main.custom_view_popular.view.*
import java.util.*

class AdapterPopular(
    var context: Context,
    private var onCLickPicture: CLickListener, private val listPopular: ArrayList<Popular>
) : RecyclerView.Adapter<AdapterPopular.PopularHolder>() {

    class PopularHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularHolder {
            return PopularHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.custom_view_popular, parent, false)
            )
    }

    override fun getItemCount(): Int {
        return listPopular.size
    }


    override fun onBindViewHolder(holder: PopularHolder, position: Int) {
        val listPopularImage = listPopular[position]
        Glide.with(context).load(listPopularImage.img)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.itemView.img_popular)
        holder.itemView.textViewPopular.text = listPopularImage.namePopular
        holder.itemView.img_popular.setOnClickListener {
            onCLickPicture.onClickPopular(listPopularImage)
        }
    }
}