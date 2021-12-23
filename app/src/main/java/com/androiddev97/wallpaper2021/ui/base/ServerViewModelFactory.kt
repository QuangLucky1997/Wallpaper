package com.androiddev97.wallpaper2021.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddev97.wallpaper2021.data.api.ApiHelper
import com.androiddev97.wallpaper2021.data.repository.SeverRepository
import com.androiddev97.wallpaper2021.ui.main.viewmodel.ServerViewModel

class ServerViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServerViewModel::class.java)) {
            return ServerViewModel(SeverRepository(apiHelper)) as T
        }
        throw IllegalThreadStateException("Unknown class name")
    }

}