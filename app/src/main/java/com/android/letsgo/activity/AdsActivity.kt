package com.android.letsgo.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.letsgo.R

class AdsActivity : BaseActivity() {

    override fun initViews(savedInstanceState: Bundle?) {

    }

    override fun layoutResId(): Int {
        return 0
    }

    override fun titleResId(): Int {
        return 0
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment, fragment.javaClass.simpleName)
        if (addToBackStack) fragmentTransaction.addToBackStack(fragment.javaClass.name)
        fragmentTransaction.commit()
    }
}