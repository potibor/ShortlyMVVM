package com.example.shortlyappipeuya.domain

import com.example.shortlyappipeuya.data.local.model.LinkModel
import com.example.shortlyappipeuya.data.repository.LinkRepository
import com.example.shortlyappipeuya.util.UseCase
import javax.inject.Inject

class FetchShortenedLinksUseCase @Inject constructor(
    private val repository: LinkRepository
) : UseCase<List<LinkModel>, UseCase.None>() {

    override suspend fun buildUseCase(params: None) = repository.fetchLinks()
}