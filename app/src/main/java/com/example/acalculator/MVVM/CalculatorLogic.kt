package com.example.acalculator.MVVM

import android.content.ContentValues.TAG
import android.util.Log
import com.example.acalculator.ListStorage
import com.example.acalculator.Operation
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.*

class CalculatorLogic {
    private val symbols = listOf('+', '-', '*','/')
    private val storage = ListStorage.getInstance()

    fun insertSymbol (display: String, symbol: String): String {
        Log.i(TAG,"Click no botão $symbol")
        return if (display.isEmpty() && symbol == "0") symbol else display + symbol
    }

    fun insertArithmeticSymbol(display: String, symbol: String): String {
        Log.i(TAG,"Click no botão $symbol")
        if(display.isNotEmpty() && display[display.length -1] !in symbols) {
            return display + symbol
        }else if (display.isNotEmpty() && display[display.length -1] in symbols) {
            val aux = display.substring(0,display.length - 1) + symbol
            return aux
        }
        return display
    }

    fun insertDot(display: String): String{
        Log.i(TAG,"Click no botão .")
        if(display.isNotEmpty() && display[display.length -1] !in symbols && !display.endsWith(".") ) {
            return "$display."
        }
        return display

    }

    fun clearScreen (display: String, symbol: String): String {
        Log.i(TAG,"Click no botão $symbol")

        var text = ""
        if (symbol == "C") {
            Log.i(TAG,"Click no botão C")
            return ""
        }else {
            Log.i(TAG,"Click no botão <")
            if(display.isNotEmpty()){
                return display.substring(0, display.length - 1)
            }
            return ""
        }

    }

    fun performOperation (expression: String): Double {
        var result = 0.0
        var expressaoFinal = expression

        if (expression.isEmpty()) {
            result = 0.0
        }else if(expression[expression.length -1] in symbols) {
            expressaoFinal = expression.substring(0, expression.length - 1 )
        }

        if(expression.isNotEmpty() && expression.contains(Regex("-? *?/?^?.?"))) {
            val expressionBuilder = ExpressionBuilder(expressaoFinal).build()
            result = expressionBuilder.evaluate()

//            list_historic?.adapter = HistoryAdapter(context!!, R.layout.item_expression, history)
//            historyAdapter.notifyDataSetChanged()
            Log.i(TAG, "Operation is  ${expressaoFinal}")
        }

        CoroutineScope(Dispatchers.IO).launch {
            storage.insert(
                Operation(
                    expressaoFinal,
                    result.toString()
                )
            )
        }
        Log.i(TAG,"Click no botão =")
        return result
    }
}