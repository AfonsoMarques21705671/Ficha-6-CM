package com.example.acalculator.MVVM_History

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import com.example.acalculator.HistoryAdapter
import com.example.acalculator.MVVM.CalculatorViewModel
import com.example.acalculator.Operation
import com.example.acalculator.R
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_history.list_historic
import kotlinx.android.synthetic.main.fragment_calculator.*

class HistoryFragment() : Fragment(), OnHistoryChanged {
    private var history = mutableListOf<Operation>()
    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        viewModel.registerListener(this)
        super.onStart()
        history()

    }

    override fun onHistoryChanged(values: MutableList<Operation>) {
        Log.i("TESTE", values.toString())
        //values?.let { history = it }
        values?.let {
            list_historic?.layoutManager = LinearLayoutManager(activity)
            list_historic?.adapter = HistoryAdapter(activity as Context, R.layout.item_expression, it)
        }

    }


    override fun onDestroy() {
        viewModel.unregisterListener()
        super.onDestroy()
    }

    fun history() {
        viewModel.getHistory()
        list_historic?.layoutManager = LinearLayoutManager(activity)
        list_historic?.adapter = HistoryAdapter(activity as Context, R.layout.item_expression, history)
    }


}
