package com.example.acalculator

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.view.*

const val EXTRA_HISTORY = "com.github.mstavares.cm.calculadora.EXTRA_HISTORY"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
        NavigationManager.goToCalculatorFragment(supportFragmentManager)
        val user = intent.getParcelableExtra<User>("EXTRA_UTILIZADOR")
        var view = nav_drawer.getHeaderView(0)
        view.title.text = user.name
        view.sub_title.text = user.email
//        list_historic?.adapter = HistoryAdapter(this, R.layout.item_expression, history)
//        historyAdapter = HistoryAdapter(this, R.layout.item_expression, history)
//        list_historic?.adapter = historyAdapter
    }

    private fun setupDrawerMenu () {
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close)
        nav_drawer.setNavigationItemSelectedListener (this)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_calculator -> NavigationManager.goToCalculatorFragment(supportFragmentManager)
            R.id.nav_history -> NavigationManager.goToHistoryFragment(supportFragmentManager)
            R.id.nav_logout -> {
                startActivity(Intent(this, activity_login::class.java))
                finish()
            }

        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START)
        else if (supportFragmentManager.backStackEntryCount == 1) finish()
        else super.onBackPressed()
    }

}