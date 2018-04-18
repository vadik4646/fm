package com.example.vtabaran.fm.util

import android.support.v4.app.Fragment
import android.widget.TextView
import com.example.vtabaran.fm.MainActivity
import com.example.vtabaran.fm.R
import com.example.vtabaran.fm.fragment.MainFragment

class UiHelper {

    companion object {
        lateinit var mainActivity: MainActivity

        fun init(activity: MainActivity) {
            mainActivity = activity
        }

        fun login(email: String) {
            mainActivity.findViewById<TextView>(R.id.usersEmail).text = email
            UiHelper.switchFragment(MainFragment.newInstance())
        }

        fun switchFragment(fragment: Fragment) {
            val transaction = mainActivity.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

}