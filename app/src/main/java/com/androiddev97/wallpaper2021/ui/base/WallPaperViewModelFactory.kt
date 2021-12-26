package com.androiddev97.wallpaper2021.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddev97.wallpaper2021.data.api.ApiHelper
import com.androiddev97.wallpaper2021.data.repository.FirebaseRepository
import com.androiddev97.wallpaper2021.ui.main.viewmodel.CategoryViewModel

class WallPaperViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(FirebaseRepository()) as T
        }
        throw IllegalAccessException("Unknow class name")
    }


}