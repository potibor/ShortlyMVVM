package com.example.shortlyappipeuya.scenes

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import com.example.shortlyappipeuya.R
import com.example.shortlyappipeuya.base.BaseFragment
import com.example.shortlyappipeuya.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(
    HomeViewModel::class, R.layout.fragment_home
) {

    private lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        homeAdapter = HomeAdapter(viewModel)
        binding.adapter = homeAdapter

        viewModel.fetchShortenedLinks()

        observeList()
        observeErrorData()
        observeCopiedText()
    }

    private fun observeList() {
        viewModel.shortenedLinksLiveData.observe(viewLifecycleOwner) { list ->
            homeAdapter.submitList(list)
            homeAdapter.notifyDataSetChanged()
        }
    }

    private fun observeCopiedText() {
        viewModel.copyTextToClipBoardLiveData.observe(viewLifecycleOwner) {
            val clipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", it)
            clipboardManager.setPrimaryClip(clipData)
        }
    }

    private fun observeErrorData() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) { error ->
            if (error) showErrorDialog()
        }
    }

    private fun showErrorDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage(getString(R.string.unknown_error))
        builder.setPositiveButton(getString(R.string.dialog_yes)) { _, _ ->
            showToast(getString(R.string.dialog_yes))
        }
        builder.setNegativeButton(getString(R.string.dialog_no)) { _, _ ->
            showToast(getString(R.string.dialog_no))
        }
        builder.setNeutralButton(getString(R.string.dialog_maybe)) { _, _ ->
            showToast(getString(R.string.dialog_maybe))
        }
        builder.show()
    }

    private fun showToast(toastDescription: String) {
        Toast.makeText(
            requireContext(),
            toastDescription, Toast.LENGTH_SHORT
        ).show()
    }
}
