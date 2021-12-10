package com.androiddev97.wallpaper2021.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androiddev97.wallpaper2021.data.model.firebase.InfoImage
import com.androiddev97.wallpaper2021.data.model.firebase.WallPaper
import com.google.firebase.database.*

class WallPaperRepository {
    fun getCategory(): LiveData<MutableList<WallPaper>> {
        val mutableData = MutableLiveData<MutableList<WallPaper>>()
        val wallPaperList = mutableListOf<WallPaper>()
        val dataWallPaper = FirebaseDatabase.getInstance().reference
        val imageWallPaper = dataWallPaper.child("CategoryBackground")
        imageWallPaper.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (snapShotWall in snapshot.children) {
                        val id = snapShotWall.child("id").getValue(String::class.java) ?: ""
                        val title = snapShotWall.child("title").getValue(String::class.java) ?: ""
                        val imgCategory =
                            snapShotWall.child("imageCategory").getValue(String::class.java) ?: ""
                        val imageList = arrayListOf<InfoImage>()
                        val childImage = snapShotWall.child("url")
                        for (image in childImage.children) {
                            val url = image.value as InfoImage
                            if (url.url.isEmpty()) continue
                            imageList.add(url)
                        }
                        wallPaperList.add(
                            WallPaper(
                                id = id,
                                title = title,
                                imgCategory = imgCategory,
                                photos = imageList
                            )
                        )
                    }
                    mutableData.value = wallPaperList
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return mutableData
    }


    fun getListPhotos(titleName: String): LiveData<MutableList<InfoImage>> {
        val mutableData = MutableLiveData<MutableList<InfoImage>>()
        val wallPaperList = mutableListOf<InfoImage>()
        val dataWallPaper = FirebaseDatabase.getInstance().reference
        val imageWallPaper =
            dataWallPaper.child("CategoryBackground").orderByChild("title").equalTo(titleName)
        imageWallPaper.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (snapShotWall in snapshot.children) {
                        val photos = snapShotWall.child("photos")
                        for (i in photos.children) {
                            val idPhoto = i.child("id").getValue(String::class.java) ?: ""
                            val urlPhoto = i.child("url").getValue(String::class.java) ?: ""
                            wallPaperList.add(InfoImage(idPhoto, urlPhoto))
                            Log.d("Main12345", "Id: $idPhoto --- url: $urlPhoto")
                        }
                    }
                    mutableData.value = wallPaperList
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return mutableData
    }



}