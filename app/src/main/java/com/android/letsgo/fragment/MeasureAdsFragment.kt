package com.android.letsgo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.android.letsgo.R
import androidx.lifecycle.ViewModelProvider
import com.android.letsgo.viewModel.MeasureAdsViewModel


class MeasureAdsFragment : Fragment() {

    private lateinit var modelView: MeasureAdsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        modelView= ViewModelProvider(this)[MeasureAdsViewModel::class.java]
        return ComposeView(requireContext()).apply {
            setContent {
                modelView.PreviewMeasureAdsScreen()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): MeasureAdsFragment = MeasureAdsFragment()
    }
}