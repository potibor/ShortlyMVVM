package com.example.shortlyappipeuya.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shortlyappipeuya.data.local.model.LinkModel


@Entity(tableName = "link")
data class LinkEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "shortenedLink") val shortenedLink: String?,
    @ColumnInfo(name = "originalLink") val originalLink: String?
) {

    fun toLinkModel(): LinkModel = LinkModel(
        id, shortenedLink = shortenedLink, originalLink = originalLink
    )

}