package com.lixan.fajardo.tawkentranceexam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import javax.inject.Inject

class ViewModelFactory<VM : ViewModel> @Inject constructor(private val vm: Lazy<VM>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return vm.get() as T
    }
}