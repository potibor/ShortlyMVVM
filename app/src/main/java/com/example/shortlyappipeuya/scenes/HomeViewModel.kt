package com.example.shortlyappipeuya.scenes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shortlyappipeuya.data.remote.model.ShortenLinkModel
import com.example.shortlyappipeuya.domain.ShortenLinkUseCase
import com.example.shortlyappipeuya.util.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shortenLinkUseCase: ShortenLinkUseCase
) : ViewModel() {

    val shortenLinkText = MutableLiveData<String?>()
    val isShortenLinkTextEmpty = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Boolean>()

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

    private fun fetchShortenedLinks(model: ShortenLinkModel) {
        shortenLinkText.value = null
        Log.d("Success", model.toString())
    }
}