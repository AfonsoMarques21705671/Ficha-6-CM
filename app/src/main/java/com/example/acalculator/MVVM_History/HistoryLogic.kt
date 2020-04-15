package com.example.acalculator.MVVM_History

import android.content.ContentValues
import android.util.Log
import com.example.acalculator.ListStorage
import com.example.acalculator.Operation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder

class HistoryLogic {

    private val storage = ListStorage.getInstance()

    fun getHistory(): List<Operation> {
        return storage.getAll()
    }

    fun deleteHistory(index: Int) {
        storage.deleteStorage(index)
    }
}