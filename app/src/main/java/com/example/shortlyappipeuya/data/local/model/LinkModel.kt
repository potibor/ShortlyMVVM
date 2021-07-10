package com.example.shortlyappipeuya.data.local.model

import com.example.shortlyappipeuya.base.ListAdapterItem
import java.io.Serializable

data class LinkModel(
    override val id: Int = 0,
    var shortenedLink: String?,
    var originalLink: String?
) : Serializable, ListAdapterItem