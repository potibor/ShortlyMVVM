package com.example.shortlyappipeuya.data.local.datasource

import com.example.shortlyappipeuya.data.database.entity.LinkEntity
import com.example.shortlyappipeuya.data.database.dao.LinksDao
import com.example.shortlyappipeuya.data.local.model.LinkModel
import javax.inject.Inject

class LinkLocalDataSource @Inject constructor(
    private val notesDao: LinksDao
) {

    fun add(link: LinkModel) {
        notesDao.addLink(
            LinkEntity(
                shortenedLink = link.shortenedLink,
                originalLink = link.originalLink
            )
        )
    }

    suspend fun remove(link: LinkModel) {
        notesDao.removeLink(
            LinkEntity(
                id = link.id,
                shortenedLink = link.shortenedLink,
                originalLink = link.originalLink
            )
        )
    }

    suspend fun getAll(): List<LinkModel> =
        notesDao.getLinks().map { link ->
            LinkEntity(
                id = link.id,
                shortenedLink = link.shortenedLink,
                originalLink = link.originalLink
            ).toLinkModel()
        }

}