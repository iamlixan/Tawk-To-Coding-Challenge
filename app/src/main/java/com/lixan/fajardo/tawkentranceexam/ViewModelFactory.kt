package com.lixan.fajardo.tawkentranceexam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import javax.inject.Inject

class ViewModelFactory<VIEWMODEL : ViewModel> @Inject constructor(private val vm: Lazy<VIEWMODEL>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return vm.get() as T
    }
}