package com.example.shortlyappipeuya.data.remote.datasource

import com.example.shortlyappipeuya.base.BaseRemoteDataSource
import com.example.shortlyappipeuya.data.remote.api.LinkAPI
import com.example.shortlyappipeuya.data.remote.model.ShortenLinkModel
import javax.inject.Inject

class LinkRemoteDataSource @Inject constructor(
    private val linkApi: LinkAPI
) : BaseRemoteDataSource() {

    suspend fun shortenLink(
        link: String
    ): ShortenLinkModel = invoke {
        with(link) {
            linkApi.shortenLink(this)
        }
    }
}