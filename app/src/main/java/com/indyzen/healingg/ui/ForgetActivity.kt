package com.indyzen.healingg.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.indyzen.healingg.R
import com.indyzen.healingg.repository.preference.Preference
import com.indyzen.healingg.ui.sigin.SigInActivity
import kotlinx.android.synthetic.main.forget_layout.*

class ForgetActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mPreference:Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forget_layout)

        mPreference= Preference(this)

        val user=mPreference.getString("email")
        usename.text=user

        val changePassword=findViewById<Button>(R.id.changePassword)
        val tvForget=findViewById<TextView>(R.id.tvForget)

        tvForget.setOnClickListener(this)
        changePassword.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.changePassword -> {
                changePassword()
            }
            R.id.tvForget -> {
                startActivity(Intent(this,SigInActivity::class.java))
                finish()
            }
            else->{}
        }
    }


    private fun validateNew():Boolean{
        return newPass.text.trim().toString()!="" && !newPass.text.trim().toString().isEmpty()
    }

    private fun validateRePass(): Boolean {
        return rePass.text.trim().toString() != "" && !rePass.text.trim().toString().isEmpty()
    }

    private fun showNewError()
    {
        newPass.error="New password shouldn't be empty"
    }

    private fun showReError() {
        rePass.error = "Confirm password shouldn't be empty"
    }

    private fun changePassword()
    {
        if (!validateNew())
        {
            showNewError()
        }else if (!validateRePass())
        {
            showReError()
        }
        else{
            if (newPass.text.toString()==rePass.text.toString()){
                Toast.makeText(this,"Passwords changed succesfully...",Toast.LENGTH_LONG).show()
                mPreference.putString("password",newPass.text.trim().toString())
                startActivity(Intent(this,SigInActivity::class.java))
                finish()

            }else{
                Toast.makeText(this,"Passwords not matched",Toast.LENGTH_LONG).show()
            }
        }
    }


}
