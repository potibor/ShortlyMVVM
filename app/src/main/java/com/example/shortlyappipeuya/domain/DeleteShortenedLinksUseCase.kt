package com.example.shortlyappipeuya.domain

import com.example.shortlyappipeuya.data.local.model.LinkModel
import com.example.shortlyappipeuya.data.repository.LinkRepository
import com.example.shortlyappipeuya.util.UseCase
import javax.inject.Inject

class DeleteShortenedLinksUseCase @Inject constructor(
    private val repository: LinkRepository
) : UseCase<Unit, DeleteShortenedLinksUseCase.Params>() {

    override suspend fun buildUseCase(params: Params) = repository.deleteLink(params.linkModel)

    data class Params(val linkModel: LinkModel)
}