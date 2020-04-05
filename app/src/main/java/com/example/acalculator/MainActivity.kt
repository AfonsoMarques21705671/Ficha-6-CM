package com.example.acalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.list_historic
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat
import java.util.*

const val EXTRA_HISTORY = "com.github.mstavares.cm.calculadora.EXTRA_HISTORY"

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    private val VISOR_KEY = "visor"

    private val pattern = "hh:mm:ss"
    private val simpleDateFormat = SimpleDateFormat(pattern)
    private var date = simpleDateFormat.format(Date())
    private val symbols = listOf('+', '-', '*','/')
    private val history = mutableListOf(Operation("1+1","2"))
//    private val historyAdapter = HistoryAdapter(this, R.layout.item_expression, history)
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,"O metodo onCreate foi invocado")
        setContentView(R.layout.activity_main)

//        list_historic?.adapter = HistoryAdapter(this, R.layout.item_expression, history)
//        historyAdapter = HistoryAdapter(this, R.layout.item_expression, history)
//        list_historic?.adapter = historyAdapter

        list_historic?.layoutManager = LinearLayoutManager(this)
        list_historic?.adapter = HistoryAdapter(this, R.layout.item_expression, history)

    }

    override fun onDestroy() {
        Log.i(TAG, "O método onDestroy foi invocado")
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        text_visor.text = savedInstanceState?.getString(VISOR_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putString(VISOR_KEY, text_visor.text.toString()) }
        super.onSaveInstanceState(outState)
    }

    fun onClickSymbol(view: View) {
        val symbol = view.tag.toString()

        Log.i(TAG,"Click no botão $symbol")
        if(text_visor.text == "0") {
            text_visor.text = symbol
        }else {
            text_visor.append(symbol)
        }
    }

    fun onClickEquals (view: View) {

        var text = text_visor.text.toString()
        Log.i(TAG,"Click no botão =")

        if (text_visor.text.isEmpty()) {
            text_visor.text = "0"
        }else if(text_visor.text[text_visor.text.length -1] in symbols){
            text = text_visor.text.substring(0, text_visor.text.length - 1 )
        }

        if(text_visor.text.isNotEmpty() && text_visor.text.contains(Regex("-? *?/?^?.?"))) {
            val expression = ExpressionBuilder(text).build()
            text_visor.text = expression.evaluate().toString()
            history.add(Operation(text,  text_visor.text.toString()))
            list_historic?.adapter = HistoryAdapter(this, R.layout.item_expression, history)
//            historyAdapter.notifyDataSetChanged()
            Log.i(TAG, "Operation is  ${text_visor.text}")
        }
        Log.i(TAG, "O resultado da expressão é ${Operation(text, text_visor.text.toString()).toString()}")

        date = simpleDateFormat.format(Date())
        Toast.makeText(this, "Calculando resultado ${date}", Toast.LENGTH_SHORT).show()

    }

    fun onClickDot(view: View) {
        Log.i(TAG,"Click no botão .")
        if(text_visor.text.isNotEmpty() && text_visor.text[text_visor.text.length -1] !in symbols && !text_visor.text.endsWith(".") ) {
            text_visor.append(".")
        }
    }

    fun onClickArithmeticSymbol (view: View) {
        val symbol = view.tag.toString()
        Log.i(TAG,"Click no botão $symbol")
        if(text_visor.text.isNotEmpty() && text_visor.text[text_visor.text.length -1] !in symbols) {
            text_visor.append(symbol)
        }else if (text_visor.text.isNotEmpty() && text_visor.text[text_visor.text.length -1] in symbols) {
            val aux = text_visor.text.substring(0,text_visor.text.length - 1) + symbol
            text_visor.text = aux
        }
    }

    fun onClickClear(view: View) {
        val symbol = view.tag.toString()
        date = simpleDateFormat.format(Date())
        var text = ""
        if (symbol == "C") {
            Log.i(TAG,"Click no botão C")
            text_visor.text = ""
            text = "Limpar tudo ${date}"
        }else {
            Log.i(TAG,"Click no botão <")
            if(text_visor.text.isNotEmpty()){
                text_visor.text = text_visor.text.substring(0, text_visor.text.length - 1)
            }
            text = "Eliminar ultimo digito ${date}"
        }
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    }

//    fun onClickHist(view: View) {
//        startActivity(Intent(this, Main2Activity::class.java))
//        finish()
//    }

    fun onClickHistory(view: View) {
        val intent = Intent(this, HistoryActivity::class.java)
        intent.apply { putParcelableArrayListExtra(EXTRA_HISTORY, ArrayList(history)) }
        startActivity(intent)
        finish()
    }

}