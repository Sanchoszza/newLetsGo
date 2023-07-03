package com.android.letsgo.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layoutResId() != 0) {
            setContentView(layoutResId())
            ButterKnife.bind(this)
        }
        if (isFinishing) return
        initViews(savedInstanceState)
    }

    abstract fun initViews(savedInstanceState: Bundle?);
    @LayoutRes
    abstract fun layoutResId(): Int;
    @StringRes
    abstract fun titleResId(): Int;
}