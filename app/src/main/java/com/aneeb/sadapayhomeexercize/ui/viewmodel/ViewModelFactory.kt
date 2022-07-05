package com.aneeb.sadapayhomeexercize.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aneeb.sadapayhomeexercize.network.TrendingRepoServiceRepository

class ViewModelFactory constructor(private val repository: TrendingRepoServiceRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TrendingRepositoriesViewModel::class.java)) {
            TrendingRepositoriesViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}