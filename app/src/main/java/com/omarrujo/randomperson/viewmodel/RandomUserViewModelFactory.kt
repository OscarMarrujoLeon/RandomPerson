package com.omarrujo.randomperson.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RandomUserViewModelFactory(private val applicationContext: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RandomUserViewModel::class.java)) {
            return RandomUserViewModel(applicationContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
