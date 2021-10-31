package com.androiddev97.wallpaper2021.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.data.model.InfoImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.custom_view_imagelist.view.*


class ViewPaper2ListImageAdapter(var context: Context) :
    RecyclerView.Adapter<ViewPaper2ListImageAdapter.ListImageHolder>() {
    private val arrayListImage = emptyList<InfoImage>()

    class ListImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListImageHolder {
        return ListImageHolder(
            LayoutInflater.from(context).inflate(R.layout.custom_view_imagelist, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListImageHolder, position: Int) {
        val listPhotos = arrayListImage[position]
        Glide.with(context).load(listPhotos).diskCacheStrategy(
            DiskCacheStrategy.AUTOMATIC
        ).into(holder.itemView.image_list_click);
    }

    override fun getItemCount(): Int {
        return arrayListImage.size
    }

}