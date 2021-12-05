package com.prerna.stackexchangeuser.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.prerna.stackexchangeuser.ui.user.UserActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //SplashScreen handler
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ goToUserActivity() }, 1500L)
    }

    //Launch UserActivity
    private fun goToUserActivity() {
        startActivity(Intent(this, UserActivity::class.java))
        finish()
    }
}