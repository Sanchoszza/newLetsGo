package com.android.letsgo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.letsgo.R


class AddTravelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_travel, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): AddTravelFragment = AddTravelFragment()
    }
}