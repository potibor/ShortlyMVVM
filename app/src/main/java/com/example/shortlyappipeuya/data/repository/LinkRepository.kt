package com.example.shortlyappipeuya.data.repository

import com.example.shortlyappipeuya.data.remote.datasource.LinkRemoteDataSource
import com.example.shortlyappipeuya.data.remote.model.ShortenLinkModel
import javax.inject.Inject

class LinkRepository @Inject constructor(
    private val remoteDataSource: LinkRemoteDataSource
) {
    suspend fun shortenLink(link: String): ShortenLinkModel {
        return remoteDataSource.shortenLink(link)
    }
}