package com.example.shortlyappipeuya.data.remote.datasource

import com.example.shortlyappipeuya.base.BaseRemoteDataSource
import com.example.shortlyappipeuya.data.remote.api.LinkService
import com.example.shortlyappipeuya.data.remote.model.ShortenLinkModel
import javax.inject.Inject

class LinkRemoteDataSource @Inject constructor(
    private val service: LinkService
) : BaseRemoteDataSource() {

    suspend fun shortenLink(
        link: String
    ): ShortenLinkModel = invoke {
        with(link) {
            service.shortenLink(this)
        }
    }
}