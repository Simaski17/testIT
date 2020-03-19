package com.ittalent.testitandroid.data.server

import com.ittalent.domain.ResultListItunes
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface TheItunesDbService {

    @POST("search?mediaType=music&limit=20")
    fun listSongsAsync(@Query("term") terms: String, @Query("offset") offset: Int): Call<ResultListItunes>

    @POST("search?entity=song")
    fun listSongsAlbumAsync(@Query("term") terms: String): Call<ResultListItunes>

}