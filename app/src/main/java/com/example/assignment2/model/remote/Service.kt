package com.example.assignment2.model.remote

import com.example.assignment2.model.MusicDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET(SEARCH)
    fun queryItunesMusic( @Query(TERM) term: String) : Call<MusicDetail>

}
