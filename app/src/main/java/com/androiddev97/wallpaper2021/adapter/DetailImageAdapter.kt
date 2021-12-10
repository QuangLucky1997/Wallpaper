package com.androiddev97.wallpaper2021.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.`interface`.CLickListener
import com.androiddev97.wallpaper2021.data.model.firebase.InfoImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.detail_custom.view.*

class DetailImageAdapter(
    var context: Context,
    private var onCLickPicture: CLickListener
) : RecyclerView.Adapter<DetailImageAdapter.DetailHolder>() {
    private var itemPicturesList = emptyList<InfoImage>()

    class DetailHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        return DetailHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.detail_custom, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        val imageDetailList = itemPicturesList[position]
        Glide.with(context).load(imageDetailList.url).centerCrop()
            .override(500, 800).diskCacheStrategy(
                DiskCacheStrategy.AUTOMATIC
            ).into(holder.itemView.ImageDetail)
        holder.itemView.ImageDetail.setOnClickListener {
            onCLickPicture.onClickShowFull(imageDetailList)
        }
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