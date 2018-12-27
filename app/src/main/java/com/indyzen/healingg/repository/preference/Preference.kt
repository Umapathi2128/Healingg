package com.indyzen.healingg.repository.preference

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Umapathi on 13/12/18.
 * Copyright Indyzen Inc, @2018
 */
class Preference(
    ctx: Context,
    val preference: SharedPreferences? = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
) {
    fun putString(key: String, value: String?) {
        preference?.edit {
            putString(key, value)
        }
    }

    fun putInt(key: String, value: Int) {
        preference?.edit {
            putInt(key, value)
        }
    }

    fun putBoolean(key: String, value: Boolean) {
        preference?.edit {
            putBoolean(key, value)
        }
    }

    fun getBoolean(key: String): Boolean? {
        return preference?.getBoolean(key, false)
    }
    fun getString(key: String): String? {
        return preference?.getString(key, "")
    }

    fun getInt(key: String): Int? {
        return preference?.getInt(key, 0)
    }


}