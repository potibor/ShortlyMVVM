package com.example.shortlyappipeuya.data.local.model

import com.example.shortlyappipeuya.base.ListAdapterItem
import java.io.Serializable

data class LinkModel(
    override val id: Int = 0,
    val shortenedLink: String?,
    val originalLink: String?,
    var isSelected: Boolean = false
) : Serializable, ListAdapterItem