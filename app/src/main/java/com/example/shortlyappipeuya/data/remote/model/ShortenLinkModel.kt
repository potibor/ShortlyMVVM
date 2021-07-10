package com.example.shortlyappipeuya.data.remote.model

import com.google.gson.annotations.SerializedName

data class ShortenLinkModel(
    val result: ShortenLinkResultModel
)

data class ShortenLinkResultModel(
    val code: String,
    @SerializedName("short_link")
    val shortLink: String,
    @SerializedName("full_short_link")
    val fullShortLink: String
)