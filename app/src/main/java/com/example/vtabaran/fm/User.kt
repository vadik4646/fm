package com.example.vtabaran.fm

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class User constructor(var email: String, var token: String, val id: Int){

    companion object {
        private lateinit var sharedPreferences: SharedPreferences

        fun init(context: Context) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun get(): User? {
            val email = sharedPreferences.getString("email", null)
            val token = sharedPreferences.getString("token", null)
            val id = sharedPreferences.getInt("id", 0)

            if (email != null && token != null && id != 0) {
                return User(email, token, id)
            }

            return null
        }

        fun set(email: String, token: String, id: Int) {
            val editor = sharedPreferences.edit()

            editor.putString("email", email).apply()
            editor.putString("token", token).apply()
            editor.putInt("id", id).apply()
        }

        fun logout() {
            val editor = sharedPreferences.edit()

            editor.remove("email")?.apply()
            editor.remove("token")?.apply()
            editor.remove("id")?.apply()
        }
    }
}