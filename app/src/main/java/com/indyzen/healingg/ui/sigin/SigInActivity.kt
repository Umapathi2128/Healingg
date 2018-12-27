package com.indyzen.healingg.ui.sigin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.indyzen.healingg.R
import com.indyzen.healingg.repository.preference.Preference
import com.indyzen.healingg.ui.ForgetActivity
import com.indyzen.healingg.ui.MainActivity
import com.indyzen.healingg.ui.signup.SignUpActivity
import com.indyzen.numberplatedetection.repository.networks.BuldRetrofit
import com.indyzen.numberplatedetection.repository.networks.RetrofitConfig
import com.indyzen.numberplatedetection.repository.networks.ServiceResponse
import kotlinx.android.synthetic.main.sign_in.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SigInActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)

        val SignIn_Button = findViewById<Button>(R.id.SignIn_Button)
        val tvForSignUp = findViewById<TextView>(R.id.tvForSignUp)
        val forget = findViewById<TextView>(R.id.forget)

        tvForSignUp.setOnClickListener(this)
        SignIn_Button.setOnClickListener(this)
        forget.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            com.indyzen.healingg.R.id.SignIn_Button -> {
                signIn()
            }
            com.indyzen.healingg.R.id.tvForSignUp -> {
                startActivity(Intent(this, SignUpActivity::class.java))
            }
            R.id.forget -> {
                startActivity(Intent(this, ForgetActivity::class.java))
            }
            else -> {
            }
        }
    }


    private fun validateEmail(): Boolean {
        return email2.text.trim().toString() != "" && !email2.text.trim().toString().isEmpty()
    }

    private fun validatePassword(): Boolean {
        return pass2.text.trim().toString() != "" && !pass2.text.trim().toString().isEmpty()
    }

    private fun showEmailError() {
        email2.error = "Email not valid"
    }

    private fun showPasswordError() {
        email2.error = "Password not valid"
    }

    private fun signIn() {


        if (!validateEmail()) {
            showEmailError()
        } else if (!validatePassword()) {
            showPasswordError()
        } else {
            login(email2.text.toString(),pass2.text.toString())
//            var mPreference = Preference(this)
//
//            val email = mPreference.getString("email")
//            val password = mPreference.getString("password")
//            if (email == email2.text.trim().toString() && pass2.text.trim().toString() == password) {
//                startActivity(Intent(this, MainActivity::class.java))
//                finish()
//            } else {
//                Toast.makeText(this, "User name password not matched...", Toast.LENGTH_LONG).show()
//            }
////
        }
    }

    private fun login(email: String, password: String) {
        val service = BuldRetrofit().getRetrofit()?.create(RetrofitConfig::class.java)
//        val fileUri = test
//        val file = File(getRealPathFromURI(fileUri))

//        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), path)
//        val source = MultipartBody.Part.createFormData("source", path.name, requestFile)
//        val image_type = RequestBody.create(okhttp3.MultipartBody.FORM, "DIRECT")
//        Log.e("source",source.toString())
//        Log.e("imageType",source.toString())

//        val requestText = RequestBody.create(MediaType.parse("multipart/form-data"), image_type)

//        service?.login("sumonsen@innovination.com", "123456")?.subscribeOn(Schedulers.io())
        service?.login(email, password)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Subscriber<ServiceResponse>() {
                override fun onNext(t: ServiceResponse?) {
                    Log.e("data", t!!.toString())
                }

                override fun onCompleted() {
                    Log.e("onCompleted", "Success")
                    startActivity(Intent(this@SigInActivity, MainActivity::class.java))
//                    finish()
                }

                override fun onError(e: Throwable?) {
                    Log.e("onError", e.toString())
                    Toast.makeText(this@SigInActivity, "Invalid UserName or Password...", Toast.LENGTH_LONG).show()
                }
            })
    }
}
