package com.indyzen.healingg.repository.preference

import android.content.SharedPreferences

/**
 * Created by Kavinraj on 30/01/18.
 * Copyright Indyzen Inc, @2018
 */
/**
 * inline function for edit shared preference
 */
inline fun SharedPreferences.edit(func:SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.func()
    editor.apply()
}