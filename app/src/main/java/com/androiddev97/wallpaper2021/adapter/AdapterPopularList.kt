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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.custom_random_pictures.view.*
import kotlinx.android.synthetic.main.item_list_popular.view.*

class AdapterPopularList (
    var context: Context,
    private var onCLickPicture: CLickListener, mListPopularPicturesModel: List<Photo>
) : RecyclerView.Adapter<AdapterPopularList.PopularListHolder>() {
    private var itemPicturesPopularList: List<Photo> = mListPopularPicturesModel


    class PopularListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularListHolder {
        return PopularListHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_popular, parent, false)
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataListImage(list: List<Photo>) {
        this.itemPicturesPopularList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return itemPicturesPopularList.size
    }

    override fun onBindViewHolder(holder: PopularListHolder, position: Int) {
        val imagePopularList = itemPicturesPopularList[position]
        Glide.with(context).load(imagePopularList.src.large).override(600, 600)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.itemView.img_list_popular)
        holder.itemView.img_list_popular.setOnClickListener {
            onCLickPicture.onClickRandom(imagePopularList, itemPicturesPopularList )
        }
        holder.itemView.cardViewPopularList.animation=AnimationUtils.loadAnimation(holder.itemView.context, R.anim.translate)


    }
}