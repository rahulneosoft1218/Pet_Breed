package com.android.petsdetails.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.petsdetails.repository.CarouselRepo

open class CommonViewModelFactory constructor(
    context: Context,
    private val repository: CarouselRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CarouselViewModel::class.java)) {
            CarouselViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }

    }
}