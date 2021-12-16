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
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.custom_random_pictures.view.*
import kotlinx.android.synthetic.main.detail_custom.view.*


class AdapterRandomPictures(
    var context: Context,
    private var onCLickPicture: CLickListener, mListRandomPicturesModel: List<ReponseUnplash>
) : RecyclerView.Adapter<AdapterRandomPictures.RandomHolder>() {
    private var itemPicturesRandomList: MutableList<ReponseUnplash> =
        mListRandomPicturesModel as MutableList<ReponseUnplash>

    class RandomHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomHolder {
        return RandomHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_random_pictures, parent, false)
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataListImage(list: MutableList<ReponseUnplash>) {
        this.itemPicturesRandomList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return itemPicturesRandomList.size
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: RandomHolder, position: Int) {
        val imageRandomList = itemPicturesRandomList[position]
        Glide.with(context).load(imageRandomList.url).centerCrop()
            .override(500, 500).diskCacheStrategy(
                DiskCacheStrategy.AUTOMATIC
            ).into(holder.itemView.img_random)
        holder.itemView.img_random.setOnClickListener {
            onCLickPicture.onClickRandom(imageRandomList)
        }
    }
}