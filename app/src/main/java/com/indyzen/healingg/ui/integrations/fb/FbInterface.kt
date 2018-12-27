package com.indyzen.healingg.ui.integrations.fb

import android.app.Activity
import android.content.Context
import com.facebook.CallbackManager
import com.facebook.login.LoginManager

/**
 * Created by Umapathi on 26/12/18.
 * Copyright Indyzen Inc, @2018
 */
interface FbInterface {
    fun fbLogin(loginManager: LoginManager, callbackManager: CallbackManager, context: Context, activity: Activity)}