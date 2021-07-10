package com.example.shortlyappipeuya.scenes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shortlyappipeuya.data.local.model.LinkModel
import com.example.shortlyappipeuya.domain.FetchShortenedLinksUseCase
import com.example.shortlyappipeuya.domain.ShortenLinkUseCase
import com.example.shortlyappipeuya.util.Failure
import com.example.shortlyappipeuya.util.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shortenLinkUseCase: ShortenLinkUseCase,
    private val fetchShortenedLinksUseCase: FetchShortenedLinksUseCase,
) : ViewModel(), HomeClickListener {

    val shortenLinkText = MutableLiveData<String?>()
    val isShortenLinkTextEmpty = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Boolean>()
    val shortenedLinksLiveData = MutableLiveData<List<LinkModel>>()


    fun onShortenButtonClicked() {
        if (shortenLinkText.value?.isNotEmpty() == true) sendLinkToBeShortened()
        else isShortenLinkTextEmpty.value = true
    }

    private fun sendLinkToBeShortened() = viewModelScope.launch {
        shortenLinkUseCase
            .run(params = ShortenLinkUseCase.Params(shortenLinkText.value ?: ""))
            .either(::handleError, ::fetchShortenedLinks)
    }

    private fun handleError(failure: Failure) {
        errorLiveData.value = true
    }

    fun fetchShortenedLinks() = viewModelScope.launch {
        shortenLinkText.value = null
        fetchShortenedLinksUseCase.run(UseCase.None)
            .either(::handleError, ::updateShortenedLinksList)
    }

    private fun updateShortenedLinksList(shortenedLinks: List<LinkModel>) {
        shortenedLinksLiveData.value = shortenedLinks
    }

    override fun linkItemDeleteClicked(model: LinkModel) {
        TODO("Not yet implemented")
    }
}