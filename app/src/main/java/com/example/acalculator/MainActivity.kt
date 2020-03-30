package com.example.acalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private var history = ""
    private val VISOR_KEY = "visor"

    private val pattern = "hh:mm:ss"
    private val simpleDateFormat = SimpleDateFormat(pattern)
    private var date = simpleDateFormat.format(Date())
    private val symbols = listOf<Char>('+', '-', '*','/')

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,"O metodo onCreate foi invocado")
        setContentView(R.layout.activity_main)

        list_historic?.adapter = HistoryAdapter (this,
            android.R.layout.simple_list_item_1, arrayListOf("1+1=2" , "2+3=5"))

//        button_exp.setOnClickListener {
//            Log.i(TAG,"Click no botão ^")
//            if(text_visor.text[text_visor.text.length -1] !in symbol && !text_visor.text.endsWith("^")) {
//                text_visor.append("^")
//            }
//        }

        button_0.setOnClickListener { onClickSymbol("0") }

        button_1.setOnClickListener { onClickSymbol("1") }

        button_2.setOnClickListener { onClickSymbol("2") }

        button_3.setOnClickListener { onClickSymbol("3") }

        button_4.setOnClickListener { onClickSymbol("4") }

        button_5.setOnClickListener { onClickSymbol("5") }

        button_6.setOnClickListener { onClickSymbol("6") }

        button_7.setOnClickListener { onClickSymbol("7") }

        button_8.setOnClickListener { onClickSymbol("8") }

        button_9.setOnClickListener { onClickSymbol("9") }

        button_adition.setOnClickListener { onClickArithmeticSymbol("+") }

        button_subtract.setOnClickListener { onClickArithmeticSymbol("-") }

        button_multiply.setOnClickListener { onClickArithmeticSymbol("*") }

        button_divide.setOnClickListener { onClickArithmeticSymbol("/") }

        button_dot.setOnClickListener { onClickDot() }

        button_hist?.setOnClickListener { onClickHist() }

        button_clearAll.setOnClickListener { onClickClear("C") }

        button_clear.setOnClickListener { onClickClear("<") }

        button_equals.setOnClickListener {onClickEquals () }

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

    private fun onClickSymbol(symbol: String) {
        Log.i(TAG,"Click no botão $symbol")
        if(text_visor.text == "0") {
            text_visor.text = symbol
        }else {
            text_visor.append(symbol)
        }
    }

    private fun onClickEquals () {

        var text = text_visor.text.toString()
        history = text_visor.text.toString()
        Log.i(TAG,"Click no botão =")
        if(text_visor.text[text_visor.text.length -1] in symbols){
            text = text_visor.text.substring(0, text_visor.text.length - 1 )
        }
        if(text_visor.text.contains(Regex("-? *?/?^?.?"))) {
            val expression = ExpressionBuilder(text).build()
            text_visor.text = expression.evaluate().toString()
        }
        Log.i(TAG, "O resultado da expressão é ${text_visor.text}")

        date = simpleDateFormat.format(Date())
        Toast.makeText(this, "Calculando resultado ${date}", Toast.LENGTH_SHORT).show()
    }

    private fun onClickDot() {
        Log.i(TAG,"Click no botão .")
        if(text_visor.text[text_visor.text.length -1] !in symbols && !text_visor.text.endsWith(".") ) {
            text_visor.append(".")
        }
    }

    private fun onClickArithmeticSymbol (symbol: String) {
        Log.i(TAG,"Click no botão $symbol")
        if(text_visor.text != "0" && text_visor.text[text_visor.text.length -1] !in symbols) {
            text_visor.append(symbol)
        }else if ( text_visor.text[text_visor.text.length -1] in symbols) {
            val aux = text_visor.text.substring(0,text_visor.text.length - 1) + symbols
            text_visor.text = aux
        }
    }

    private fun onClickClear(symbol: String) {
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

    private fun onClickHist() {
        Log.i(TAG,"Click no botão Hist.")
        text_visor.text = history

        date = simpleDateFormat.format(Date())
        Toast.makeText(this, "Ultimo calculo ${date}", Toast.LENGTH_SHORT).show()
    }
}
