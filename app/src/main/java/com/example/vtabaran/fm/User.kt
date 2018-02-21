package com.example.vtabaran.fm

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class User constructor(var email: String, var token: String){

    companion object {
        private lateinit var sharedPreferences: SharedPreferences

        fun init(context: Context) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun get(): User? {
            val email = sharedPreferences.getString("email", null)
            val token = sharedPreferences.getString("token", null)

            if (email != null && token != null) {
                return User(email, token)
            }

            return null
        }

        fun set(email: String, token: String) {
            val editor = sharedPreferences.edit()

            editor.putString("email", email).apply()
            editor.putString("token", token).apply()
        }

        fun logout() {
            val editor = sharedPreferences.edit()

            editor.remove("email")?.apply()
            editor.remove("token")?.apply()
        }
    }
}