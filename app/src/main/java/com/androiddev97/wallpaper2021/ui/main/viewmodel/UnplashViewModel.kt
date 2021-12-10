package com.androiddev97.wallpaper2021.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.androiddev97.wallpaper2021.data.repository.UnplashRepository
import com.androiddev97.wallpaper2021.utils.Resources
import kotlinx.coroutines.Dispatchers

class UnplashViewModel(private val unplashRepository: UnplashRepository) : ViewModel() {
    fun getPictures(clint_id: String, page: Int, perPage: Int) = liveData(Dispatchers.IO)
    {
        emit(Resources.loading(data = null))
        try {
            emit(Resources.success(data = unplashRepository.getPictures(clint_id, page, perPage)))
        } catch (exception: Exception) {
            emit(Resources.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


}