package com.androiddev97.wallpaper2021.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androiddev97.wallpaper2021.data.model.firebase.InfoImage
import com.androiddev97.wallpaper2021.data.model.firebase.WallPaper
import com.androiddev97.wallpaper2021.data.repository.FirebaseRepository

class CategoryViewModel(private val firebaseRepository: FirebaseRepository) : ViewModel() {
    fun fetchDataFromFireBase(): LiveData<MutableList<WallPaper>> {
        val mutableData = MutableLiveData<MutableList<WallPaper>>()
        firebaseRepository.getCategory().observeForever { wallList ->
            mutableData.value = wallList
        }
        return mutableData
    }

    fun fetchInfoImage(titleString: String): LiveData<MutableList<InfoImage>> {
        val mutableData = MutableLiveData<MutableList<InfoImage>>()
        firebaseRepository.getListPhotos(titleString).observeForever { listPhotos ->
            mutableData.value = listPhotos
        }
        return mutableData
    }

}