package com.androiddev97.wallpaper2021.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.`interface`.CLickListener
import com.androiddev97.wallpaper2021.data.model.WallPaper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.wallpaper_adapter.view.*

class WallpaperAdapter(
    private val onClickImage: CLickListener,
    var context: Context
) : RecyclerView.Adapter<WallpaperAdapter.WallPaperHolder>() {
    private var wallPaperList = emptyList<WallPaper>()

    class WallPaperHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallPaperHolder {
        return WallPaperHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WallPaperHolder, position: Int) {
        val wallList = wallPaperList[position]
        Glide.with(context).load(wallList.imgCategory).override(400, 400).diskCacheStrategy(
            DiskCacheStrategy.AUTOMATIC
        ).into(holder.itemView.ImageShort)
        holder.itemView.TextViewDescription.text = wallList.title
        holder.itemView.CardViewCategory.setOnClickListener {
            onClickImage.onClick(wallList)
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(wallpaper: List<WallPaper>) {
        this.wallPaperList = wallpaper
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return wallPaperList.size
    }


}