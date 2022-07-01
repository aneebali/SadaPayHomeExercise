package com.aneeb.sadapayhomeexercize.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aneeb.sadapayhomeexercize.model.Item
import com.aneeb.sadapayhomeexercize.model.TrendingRepository
import com.aneeb.sadapayhomeexercize.network.TrendingRepoServiceRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingRepositoriesViewModel constructor(private val repository: TrendingRepoServiceRepository) :
    ViewModel() {

    val movieList = MutableLiveData<List<Item>>()
    val errorMessage = MutableLiveData<String>()

    fun getTrendingRepositories() {

        val response = repository.getTrendingRepositories()
        response.enqueue(object : Callback<TrendingRepository> {
            override fun onResponse(
                call: Call<TrendingRepository>,
                response: Response<TrendingRepository>
            ) {
                movieList.postValue(response.body()?.items)
            }

            override fun onFailure(call: Call<TrendingRepository>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}