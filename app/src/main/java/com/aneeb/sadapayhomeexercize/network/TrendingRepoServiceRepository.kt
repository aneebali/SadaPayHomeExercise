package com.aneeb.sadapayhomeexercize.network

class TrendingRepoServiceRepository constructor(private val retrofitService: RetrofitService) {

    fun getTrendingRepositories() = retrofitService.getTrendingRepositories()
}