package com.example.acalculator.MVVM

import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    private var listener: OnDisplayChanged? = null
    private val calculatorLogic = CalculatorLogic()
    var display: String = ""

    private fun notifyOnDisplayChanged() {
        listener?.onDisplayChanged(display)
    }

    fun registerListener(listener: OnDisplayChanged) {
        this.listener = listener
        listener.onDisplayChanged(display)
    }

    fun unregisterListener() {
        listener = null
    }

    fun onClickSymbol (symbol: String) {
        display = calculatorLogic.insertSymbol(display, symbol)
        notifyOnDisplayChanged()
    }

    fun onClickDot () {
        display = calculatorLogic.insertDot(display)
        notifyOnDisplayChanged()
    }

    fun onclickClear (symbol: String) {
        display = calculatorLogic.clearScreen(display, symbol)
        notifyOnDisplayChanged()
    }

    fun onClickArithmeticSymbol(symbol: String) {
        display = calculatorLogic.insertArithmeticSymbol(display, symbol)
        notifyOnDisplayChanged()
    }

    fun onClickEquals() {
        display = calculatorLogic.performOperation(display).toString()
        notifyOnDisplayChanged()
    }
}