package com.example.acalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main2.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val operations = intent.getParcelableArrayListExtra<Operation>(EXTRA_HISTORY)
        list_historic?.layoutManager = LinearLayoutManager(this)
        list_historic?.adapter = HistoryAdapter(this, R.layout.item_expression, operations)

        NavigationManager.goToHistoryFragment(supportFragmentManager)

//        list_historic.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
//            Toast.makeText(this, "Expressão " + operations.get(id.toInt()).expressao, Toast.LENGTH_SHORT).show()
//        }

//        FAB.setOnClickListener {
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }

    }
}
