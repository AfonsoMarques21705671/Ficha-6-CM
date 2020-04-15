package com.example.acalculator.MVVM_History

import com.example.acalculator.Operation

interface OnHistoryChanged {
    fun onHistoryChanged(values: MutableList<Operation>)

}