package com.indyzen.healingg.ui.integrations

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.leelavathi.gmailandfbintegration.DetailsScreen
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.indyzen.healingg.R
import com.indyzen.healingg.ui.integrations.fb.Integration
import com.indyzen.healingg.ui.sigin.SigInActivity
import com.indyzen.healingg.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_integrations.*
import java.util.*


class IntegrationsActivity : AppCompatActivity(), View.OnClickListener {
    var integration = Integration()
    lateinit var loginManager: LoginManager
    lateinit var callBackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_integrations)
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

        btnFb.setOnClickListener(this)
        btnGmail.setOnClickListener(this)
        signIn_email.setOnClickListener(this)
        signUp_email.setOnClickListener(this)


        callBackManager = CallbackManager.Factory.create()
        loginManager = LoginManager.getInstance()

    }

    override fun onClick(v: View?) {
        var activity = DetailsScreen()
        integration.fbLogin(loginManager, callBackManager, this, activity)
        when (v) {
            btnFb -> {
//                Toast.makeText(this, "adfv", Toast.LENGTH_LONG).show()
                loginManager.logInWithReadPermissions(this, Arrays.asList("email"))
            }
            btnGmail->{Snackbar.make(rootLayout,"Work under construction...:P",Snackbar.LENGTH_LONG).show()}
            signIn_email->{startActivity(Intent(this,SigInActivity::class.java))}
            signUp_email->{startActivity(Intent(this,SignUpActivity::class.java))}
            else -> {
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callBackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}
