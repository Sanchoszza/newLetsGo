package com.android.letsgo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.letsgo.R

import butterknife.ButterKnife

import butterknife.Unbinder


abstract class BaseFragment : Fragment() {

    protected fun initViews(){}
    protected abstract fun layoutId(): Int

    var unbinder: Unbinder? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutId(), container, false)
        unbinder = ButterKnife.bind(this, view)
        initViews()
        return view
    }

    override fun onDestroyView() {
        unbinder?.unbind()
        super.onDestroyView()
    }

}