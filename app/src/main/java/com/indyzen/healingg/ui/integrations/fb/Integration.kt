package com.indyzen.healingg.ui.integrations.fb

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.indyzen.healingg.repository.preference.Preference
import java.net.URL

/**
 * Created by Umapathi on 26/12/18.
 * Copyright Indyzen Inc, @2018
 */
class Integration:FbInterface {

    override fun fbLogin(loginManager: LoginManager, callbackManager: CallbackManager, context: Context, activity: Activity) {
        loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                val graph = GraphRequest.newMeRequest(result?.accessToken) { `object`, response ->
                    val name: String? = `object`?.getString("first_name") + " " + `object`.getString("last_name")
                    if (`object`.has("email")) {
                        val email: String? = `object`?.getString("email")
                        Preference(context).putString("MailId", email!!)
                    } else {
                        Preference(context).putString("MailId", "no email")
                    }
                    Preference(context).putString("eName", name!!)
                    val profilePic = URL("https://graph.facebook.com/" + `object`.getString("id") + "/picture?width=500&height=500")
                    Preference(context).putString("Url", profilePic.toString())
                    Preference(context).putString("Url", profilePic.toString())
                    context.startActivity(Intent(context, activity::class.java))
                }
                val parameter = Bundle()
                parameter.putString("fields", "id,first_name,last_name,email")
                graph.parameters = parameter
                graph.executeAsync()
            }

            override fun onCancel() {
                Log.e("cancel","cancel")
            }

            override fun onError(error: FacebookException?) {
                Log.e("error",error.toString())

            }

        })
    }
}