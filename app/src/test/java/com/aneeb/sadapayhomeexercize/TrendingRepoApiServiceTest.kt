package com.aneeb.sadapayhomeexercize

import com.aneeb.sadapayhomeexercize.network.RetrofitService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TrendingRepoApiServiceTest : BaseTest() {

    private lateinit var service: RetrofitService

    @Before
    fun setup() {
        val url = mockWebServer.url("/")
        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
            .create(RetrofitService::class.java)
    }

    @Test
    fun api_service() {
        enqueue("trending_repo.json")
        val call = service.getTrendingRepositories()
        val apiResponse = call.execute().body()
        assertNotNull(apiResponse)
        assertTrue("The list was empty", apiResponse?.items?.isNotEmpty() ?: false)
        assertEquals("The IDs did not match", "23096959", apiResponse?.items?.get(0)?.id.toString())

    }
}