package com.example.shortlyappipeuya.data.remote.api

import com.example.shortlyappipeuya.data.remote.model.ShortenLinkModel
import retrofit2.http.GET
import retrofit2.http.Query

interface LinkAPI {

    @GET(SHORTEN)
    suspend fun shortenLink(
        @Query("url") link: String
    ): ShortenLinkModel

    companion object {
        const val SHORTEN = "shorten"
    }
}