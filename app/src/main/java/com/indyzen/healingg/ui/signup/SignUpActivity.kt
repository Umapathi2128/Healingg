package com.indyzen.healingg.ui.signup

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.indyzen.healingg.R
import com.indyzen.healingg.ui.MainActivity
import com.indyzen.healingg.ui.sigin.SigInActivity
import kotlinx.android.synthetic.main.sign_up_screen.*
import com.indyzen.healingg.R.id.email
import com.indyzen.healingg.repository.preference.Preference
import java.util.regex.Pattern


class SignUpActivity : AppCompatActivity(), View.OnClickListener {

//
//    private var isEmail : Boolean = false
//    private var isPassword : Boolean = false
//    private var isConfirm : Boolean = false

    lateinit  var mPreferences: Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.indyzen.healingg.R.layout.sign_up_screen)

//        signUp_root.background=resources.getDrawable(R.drawable.splash_background)

        mPreferences= Preference(this)

        val tvForSignUp = findViewById<TextView>(R.id.tvForSignIn)
        val SignUp_Button = findViewById<Button>(R.id.SignUp_Button)

        tvForSignUp.setOnClickListener(this)
        SignUp_Button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            com.indyzen.healingg.R.id.SignUp_Button -> {
                signUp()
            }
            com.indyzen.healingg.R.id.tvForSignIn -> {
                startActivity(Intent(this, SigInActivity::class.java))
            }
            else -> {
            }
        }
    }


    private fun signUp() {



        if (!validateEmail()) {
            showEmailError()
        } else if (!validatePassword()) {
            showPasswordError()
        }else if (!validateconfromPassword()) showConirmError()
        else{
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.trim().toString()).matches()) {
                if (conform_pass.text.trim().toString() == pass.text.trim().toString()) {

                    mPreferences.putString("email",email.text.toString())
                    mPreferences.putString("password",pass.text.toString())
                    startActivity(Intent(this,SigInActivity::class.java))
                    finish()
                } else {
                    conform_pass.error = "Password not matched"
                }


            } else {
                email.error = "Email not exist"

            }
        }
    }


    private fun validateEmail(): Boolean {
        return  email.text.trim().toString() != "" && !email.text.trim().toString().isEmpty()
    }

    private fun validatePassword(): Boolean {
        return pass.text.trim().toString() != "" && !pass.text.trim().toString().isEmpty()
    }

    private fun validateconfromPassword(): Boolean {
        return conform_pass.text.trim().toString() != "" && !conform_pass.text.trim().toString().isEmpty()
    }

    private fun showEmailError() {
        email.error = "Invalid email Id "
    }

    private fun showPasswordError() {
        email.error = "password not be null"
    }

    private fun showConirmError() {
        email.error = "Confirm password not be null"
    }
}
