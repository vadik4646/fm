package com.example.vtabaran.fm

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.vtabaran.fm.adapter.IncomeListAdapter
import com.example.vtabaran.fm.fragment.IncomeFragment
import com.example.vtabaran.fm.fragment.LoginFragment
import com.example.vtabaran.fm.fragment.MainFragment
import com.example.vtabaran.fm.service.repository.Repository
import com.example.vtabaran.fm.util.UiHelper


class MainActivity :
        AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        IncomeFragment.OnFragmentInteractionListener,
        MainFragment.OnFragmentInteractionListener,
        LoginFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
        if (findViewById<View>(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return
            }
            val firstFragment = MainFragment()
            firstFragment.arguments = intent.extras
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit()
        }

        User.init(this)
        Repository.init(this)
        UiHelper.init(this)
        IncomeListAdapter.init(this)
    }

    override fun onBackPressed() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("CommitTransaction")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_main -> {
                UiHelper.switchFragment(MainFragment.newInstance())
            }
            R.id.nav_income -> {
                UiHelper.switchFragment(IncomeFragment.newInstance())
            }
            R.id.nav_manage -> {
//                switchFragment(IncomeFragment())
            }
            R.id.nav_login -> {
                UiHelper.switchFragment(LoginFragment.newInstance())
            }
            R.id.nav_register -> {
//                switchFragment(IncomeFragment())
            }
        }

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}
