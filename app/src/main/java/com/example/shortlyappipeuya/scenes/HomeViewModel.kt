package com.example.shortlyappipeuya.scenes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    val shortenLinkText = MutableLiveData<String>()
    val isShortenLinkTextEmpty = MutableLiveData<Boolean>()

    fun onShortenButtonClicked() {
        if (shortenLinkText.value?.isNotEmpty() == true) sendLinkToBeShortened()
        else isShortenLinkTextEmpty.value = true
    }

    private fun sendLinkToBeShortened() {

    }
}