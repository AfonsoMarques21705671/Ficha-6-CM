package com.example.acalculator.MVVM

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import com.example.acalculator.*
import kotlinx.android.synthetic.main.activity_history.list_historic
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_calculator.view.*
import java.text.SimpleDateFormat
import java.util.*

class CalculatorFragment : Fragment(), OnDisplayChanged{
    private val TAG = MainActivity::class.java.simpleName

    private val history = mutableListOf(Operation("1+1", "2"))
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)

        ButterKnife.bind(this, view)

        list_historic?.layoutManager = LinearLayoutManager(context)
        list_historic?.adapter = HistoryAdapter(context!!, R.layout.item_expression, history)

        return view
    }

    @Optional 
    @OnClick(
        R.id.button_0,
        R.id.button_1,
        R.id.button_2,
        R.id.button_3,
        R.id.button_4,
        R.id.button_5,
        R.id.button_6,
        R.id.button_7,
        R.id.button_8,
        R.id.button_9
    )
    fun onClickSymbol(view: View) {
        viewModel.onClickSymbol(view.tag.toString())
    }

    @OnClick(R.id.button_equals)
    fun onClickEquals (view: View) {
        viewModel.onClickEquals()
    }

    @OnClick(R.id.button_dot)
    fun onClickDot(view: View) {
        viewModel.onClickDot()
    }

    @OnClick(
        R.id.button_adition,
        R.id.button_subtract,
        R.id.button_multiply,
        R.id.button_divide
    )
    fun onClickArithmeticSymbol (view: View) {
        viewModel.onClickArithmeticSymbol(view.tag.toString())
    }

    @OnClick(
        R.id.button_clear,
        R.id.button_clearAll
    )
    fun onClickClear(view: View) {
        viewModel.onclickClear(view.tag.toString())
    }

    @Optional
    @OnClick(R.id.button_hist)
    fun onClickHistory(view: View) {

    }

    override fun onStart() {
        viewModel.registerListener(this)
        super.onStart()
    }

    override fun onDisplayChanged(value: String?) {
        value?.let { text_visor.text = it }
    }

    override fun onDestroy() {
        viewModel.unregisterListener()
        super.onDestroy()
    }

}
