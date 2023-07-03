package com.android.letsgo.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.android.letsgo.R
import com.android.letsgo.fragment.StartAppFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : BaseActivity() {

    var auth: FirebaseAuth?= null
    var user: FirebaseUser?= null
    var lon: Double?= null
    var lat: Double?= null
    var handler: Handler = Handler()
    var runnable: Runnable?= null

    private var MY_PERMISSION_REQUEST_LOCATION: Int = 859


    override fun initViews(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        user = auth!!.currentUser

        
        if (user != null) startActivity(
            Intent(
                this,
                WelcomeActivity::class.java
            )
        ) else {
            replaceFragment(StartAppFragment.newInstance(), false)
        }
    }

    override fun onStart() {
        super.onStart()
        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, 5000)
//            getPosition()
        }.also { runnable = it }, 5000)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable!!)
    }

    override fun layoutResId(): Int {
        return R.layout.activity_main
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

