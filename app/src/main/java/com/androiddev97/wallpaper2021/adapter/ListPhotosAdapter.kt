package com.androiddev97.wallpaper2021.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.data.model.firebase.InfoImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.custom_view_imagelist.view.*
import kotlinx.android.synthetic.main.detail_custom.view.*

class ListPhotosAdapter(var context: Context):
    RecyclerView.
    Adapter<ListPhotosAdapter.ListPhotosHolder>() {


    private var itemPicturesList = emptyList<InfoImage>()
    class ListPhotosHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPhotosHolder {
        return ListPhotosHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_view_imagelist, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListPhotosHolder, position: Int) {
      val listPhotos=itemPicturesList[position]
        Glide.with(context).load(listPhotos.url).diskCacheStrategy(
                DiskCacheStrategy.AUTOMATIC
            ).into(holder.itemView.ImageDetail)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataListImage(imgList:List<InfoImage>) {
        this.itemPicturesList = imgList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return itemPicturesList.size
    }
}