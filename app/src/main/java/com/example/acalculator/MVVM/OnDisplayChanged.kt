package com.example.acalculator.MVVM

import com.example.acalculator.MVVM_History.OnHistoryChanged
import com.example.acalculator.Operation

interface OnDisplayChanged : OnHistoryChanged {
    fun onDisplayChanged(value: String?)
}