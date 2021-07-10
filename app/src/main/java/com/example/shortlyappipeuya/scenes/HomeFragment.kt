package com.example.shortlyappipeuya.scenes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.viewModels
import com.example.shortlyappipeuya.R
import com.example.shortlyappipeuya.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding

    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        homeAdapter = HomeAdapter(viewModel)
        binding.adapter = homeAdapter

        viewModel.fetchShortenedLinks()
        observeList()
        observeErrorData()
    }

    private fun observeList() {
        viewModel.shortenedLinksLiveData.observe(viewLifecycleOwner) { list ->
            homeAdapter.submitList(list)
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
        builder.setPositiveButton(getString(R.string.dialog_yes)) { dialog, which ->
            showToast(getString(R.string.dialog_yes))
        }
        builder.setNegativeButton(getString(R.string.dialog_no)) { dialog, which ->
            showToast(getString(R.string.dialog_no))
        }
        builder.setNeutralButton(getString(R.string.dialog_maybe)) { dialog, which ->
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