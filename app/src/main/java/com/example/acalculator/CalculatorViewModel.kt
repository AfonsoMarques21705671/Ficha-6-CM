package com.example.acalculator

import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    private val calculatorLogic = CalculatorLogic()
    var display: String = ""

    fun onClickSymbol (symbol: String): String {
        display = calculatorLogic.insertSymbol(display, symbol)
        return display
    }

    fun onClickEquals(): String {
        val result = calculatorLogic.performOperation(display)
        display = result.toString()
        return display
    }
}