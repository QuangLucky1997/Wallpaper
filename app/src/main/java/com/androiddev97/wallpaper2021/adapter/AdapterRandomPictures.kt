package com.androiddev97.wallpaper2021.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.`interface`.CLickListener
import com.androiddev97.wallpaper2021.data.model.unplash.ReponseUnplash
import com.bumptech.glide.Glide


class AdapterRandomPictures (
    var context: Context,
    private var onCLickPicture: CLickListener
) : RecyclerView.Adapter<AdapterRandomPictures.RandomHolder>() {
    private var itemPicturesRandomList = emptyList<ReponseUnplash>()

    class RandomHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomHolder {
        return RandomHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_random_pictures, parent, false)
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataListImage(imgList:List<ReponseUnplash>) {
        this.itemPicturesRandomList = imgList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return itemPicturesRandomList.size
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: RandomHolder, position: Int) {
        val imageRandomList=itemPicturesRandomList[position]
        Glide.with(context).load(imageRandomList.url.small)
    }
}