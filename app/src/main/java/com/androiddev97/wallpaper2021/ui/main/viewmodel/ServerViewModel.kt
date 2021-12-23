package com.androiddev97.wallpaper2021.ui.main.viewmodel

import android.app.DownloadManager
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.androiddev97.wallpaper2021.data.repository.SeverRepository
import com.androiddev97.wallpaper2021.utils.Resources
import kotlinx.coroutines.Dispatchers

class ServerViewModel(private val severRepository: SeverRepository) : ViewModel() {
    fun getPictures(clint_id: String, page: Int, perPage: Int) = liveData(Dispatchers.IO)
    {
        emit(Resources.loading(data = null))
        try {
            Log.d("Main123456789", "clint_id:$clint_id")
            emit(Resources.success(data = severRepository.getPictures(clint_id, page, perPage)))
        } catch (exception: Exception) {
            Log.d("Main12345678", exception.message.toString())

            emit(Resources.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getPicturesPexel(page: Int, perPage: Int) = liveData(Dispatchers.IO)
    {
        emit(Resources.loading(data = null))
        try {
            emit(Resources.success(data = severRepository.getPicturesPexel(page, perPage)))
        } catch (exception: Exception) {
            emit(Resources.error(data = null, message = exception.message ?: "Error Occurred!"))
        }

    }

    fun searchPictures(queryString: String, perPage: Int) = liveData(Dispatchers.IO)
    {
        emit(Resources.loading(data = null))
        try {
            emit(Resources.success(data = severRepository.searchPexel(queryString, perPage)))
        } catch (exception: Exception) {
            emit(Resources.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


}