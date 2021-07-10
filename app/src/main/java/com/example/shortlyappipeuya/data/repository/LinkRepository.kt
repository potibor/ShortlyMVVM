package com.example.shortlyappipeuya.data.repository

import com.example.shortlyappipeuya.data.local.datasource.LinkLocalDataSource
import com.example.shortlyappipeuya.data.local.model.LinkModel
import com.example.shortlyappipeuya.data.remote.datasource.LinkRemoteDataSource
import com.example.shortlyappipeuya.data.remote.model.ShortenLinkModel
import javax.inject.Inject

class LinkRepository @Inject constructor(
    private val remoteDataSource: LinkRemoteDataSource,
    private val localDataSource: LinkLocalDataSource
) {
    suspend fun shortenLink(link: String): ShortenLinkModel {
        val response = remoteDataSource.shortenLink(link)
        addLink(
            shortenedLink = response.result.fullShortLink,
            originalLink = response.result.originalLink
        )
        return response
    }

    suspend fun fetchLinks(): List<LinkModel> {
        return localDataSource.getAll()
    }

    private fun addLink(shortenedLink: String?, originalLink: String?) {
        return localDataSource.add(
            LinkModel(
                shortenedLink = shortenedLink,
                originalLink = originalLink
            )
        )
    }

    suspend fun deleteLink(linkModel: LinkModel) {
        return localDataSource.remove(link = linkModel)
    }
}