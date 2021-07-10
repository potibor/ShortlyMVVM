package com.example.shortlyappipeuya.domain

import com.example.shortlyappipeuya.data.repository.LinkRepository
import com.example.shortlyappipeuya.data.remote.model.ShortenLinkModel
import com.example.shortlyappipeuya.util.UseCase
import javax.inject.Inject

class ShortenLinkUseCase @Inject constructor(
    private val repository: LinkRepository
) : UseCase<ShortenLinkModel, ShortenLinkUseCase.Params>() {

    override suspend fun buildUseCase(params: Params) =
        repository.shortenLink(params.link)

    data class Params(
        val link: String
    )
}