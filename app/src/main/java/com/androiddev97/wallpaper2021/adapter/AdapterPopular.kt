package com.androiddev97.wallpaper2021.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androiddev97.wallpaper2021.*
import com.androiddev97.wallpaper2021.`interface`.CLickListener
import com.androiddev97.wallpaper2021.data.model.popular.Popular
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.custom_header_item_viewpaper.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random.Default.nextInt


class AdapterPopular(
    var context: Context,
    private var onCLickPicture: CLickListener, private val listPopularHeader: ArrayList<Popular>,
) : RecyclerView.Adapter<AdapterPopular.PopularHolder>() {

    class PopularHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularHolder {
        return PopularHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_header_item_viewpaper, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listPopularHeader.size
    }


    override fun onBindViewHolder(holder: PopularHolder, position: Int) {
        val listPopularImage = listPopularHeader[position]
        Glide.with(context).load(listPopularImage.img)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.itemView.img_list_popular)
        holder.itemView.textHeaderItem.text = listPopularImage.namePopular
        holder.itemView.text_count_item.text = listPopularImage.amount.toString()
        val androidColors: IntArray = context.resources.getIntArray(R.array.androidcolors)
        val randomAndroidColor = androidColors[nextInt(androidColors.size)]
        holder.itemView.view_bg_header.setCardBackgroundColor(randomAndroidColor)
        holder.itemView.view_bg_header.setOnClickListener {
            onCLickPicture.onClickPopular(listPopularImage)
        }
    }
}