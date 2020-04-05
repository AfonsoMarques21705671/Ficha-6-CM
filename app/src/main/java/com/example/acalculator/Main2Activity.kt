package com.example.acalculator

//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.AdapterView
//import android.widget.Toast
//import kotlinx.android.synthetic.main.activity_main2.*
//
//class Main2Activity : AppCompatActivity() {
//    private lateinit var historyAdapter: HistoryAdapter
//    val history = mutableListOf(Operation("1+1","2"), Operation("10+10","20"))
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)
//        historyAdapter = HistoryAdapter(this, R.layout.item_expression, history)
//        historic?.adapter = historyAdapter
//
//        historic.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
//            Toast.makeText(this, "Expressão " + history.get(id.toInt()).expressao,Toast.LENGTH_SHORT).show()
//        }
//
//        fab.setOnClickListener {
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }
//    }
//
//}
