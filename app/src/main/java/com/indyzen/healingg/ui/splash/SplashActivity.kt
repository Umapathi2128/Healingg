package com.indyzen.healingg.ui.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import com.indyzen.healingg.R
import com.indyzen.healingg.ui.MainActivity
import com.indyzen.healingg.ui.integrations.IntegrationsActivity
import com.indyzen.healingg.ui.sigin.SigInActivity
import com.indyzen.healingg.ui.signup.SignUpActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.splash_screen)

//        this.requestWindowFeature(Window.FEATURE_NO_TITLE)


        Handler().postDelayed({
            startActivity(Intent(this, IntegrationsActivity::class.java))
            finish()
        },3000)
    }
}
