package com.example.acalculator.MVVM_History

import androidx.lifecycle.ViewModel
import com.example.acalculator.MVVM.OnDisplayChanged
import com.example.acalculator.Operation

class HistoryViewModel : ViewModel() {
    private var listener: OnHistoryChanged? = null
    private val historyLogic = HistoryLogic()
    var listHistory = mutableListOf<Operation>()

    private fun notifyOnHistoryChanged() {
        listener?.onHistoryChanged(listHistory)
    }

    fun registerListener(listener: OnHistoryChanged) {
        this.listener = listener
        listener.onHistoryChanged(listHistory)
    }

    fun unregisterListener() {
        listener = null
    }

    fun getHistory() {
        listHistory = historyLogic.getHistory().toMutableList()
        notifyOnHistoryChanged()
    }

    fun deleteHistory(position: Int) {
        historyLogic.deleteHistory(position)
        notifyOnHistoryChanged()
    }
}