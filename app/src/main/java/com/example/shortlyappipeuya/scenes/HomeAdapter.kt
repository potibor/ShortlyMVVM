package com.example.shortlyappipeuya.scenes

import com.example.shortlyappipeuya.R
import com.example.shortlyappipeuya.base.BaseListAdapter
import com.example.shortlyappipeuya.data.local.model.LinkModel
import com.example.shortlyappipeuya.databinding.ItemShortenedLinkBinding

class HomeAdapter(
    private val listener: HomeClickListener
) : BaseListAdapter<ItemShortenedLinkBinding, LinkModel>() {

    override val layoutRes: Int = R.layout.item_shortened_link

    override fun bind(
        binding: ItemShortenedLinkBinding,
        item: LinkModel
    ) {
        binding.model = item
        binding.listener = listener
    }
}