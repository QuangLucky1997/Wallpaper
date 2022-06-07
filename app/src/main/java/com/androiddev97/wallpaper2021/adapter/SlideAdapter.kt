package com.androiddev97.wallpaper2021.adapter

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.data.model.pexel.Photo
import com.androiddev97.wallpaper2021.data.model.pexel.PhotoList
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.custom_view_imagelist.view.*

class SlideAdapter(
    private val context: Context,
    private var listImage: List<Photo>
) :
    RecyclerView.Adapter<SlideAdapter.SlideHolder>() {

    class SlideHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SlideHolder(inflater.inflate(R.layout.custom_view_imagelist, parent, false))
    }

    override fun onBindViewHolder(holder: SlideHolder, position: Int) {
        val listData = listImage[position]
        Glide.with(context).load(listData.url).into(holder.itemView.img_Walpaper)
        holder.itemView.img_Walpaper.setOnClickListener {
           // click.onClick(listData.image)
        }
    }

    override fun getItemCount(): Int {
        return listImage.size
    }


//    interface clickImage {
//        fun onClick(img:Int)
//    }
}