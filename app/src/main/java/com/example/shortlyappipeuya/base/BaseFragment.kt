package com.example.shortlyappipeuya.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

abstract class BaseFragment<VM : ViewModel, Binding : ViewDataBinding>(
    clazz: KClass<VM>,
    private val layoutResId: Int
) : Fragment() {

    val viewModel: VM by lazy { ViewModelProvider(this).get(clazz.java) }
    lateinit var binding: Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }
}
