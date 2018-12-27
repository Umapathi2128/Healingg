package com.example.leelavathi.gmailandfbintegration

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.facebook.login.LoginManager
import com.indyzen.healingg.R
import com.indyzen.healingg.repository.preference.Preference
import com.indyzen.healingg.ui.integrations.IntegrationsActivity
import kotlinx.android.synthetic.main.details_screen.*


class DetailsScreen : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_screen)
//        var signOut = findViewById<Button>(R.id.signOut)
        fbSignOut.setOnClickListener(this)
        val name = Preference(this).getString("eName")
        val mail = Preference(this).getString("MailId")
        val url = Preference(this).getString("Url")
        eName.text = name
        eMail.text = mail
        if (url!!.isEmpty()) {
            Glide.with(applicationContext).load(R.drawable.ic_launcher_background)
                .thumbnail(0.5f)
                .into(profilePic)
        } else {
            Glide.with(applicationContext).load(url)
                .thumbnail(0.5f)
                .into(profilePic)
        }


    }

    override fun onClick(v: View?) {
        when (v) {
            fbSignOut -> {
                LoginManager.getInstance().logOut()
                startActivity(Intent(this, IntegrationsActivity::class.java))
                finish()
            }
        }
    }


}