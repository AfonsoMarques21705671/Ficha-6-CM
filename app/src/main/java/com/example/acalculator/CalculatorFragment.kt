package com.example.acalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.activity_history.list_historic
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_calculator.view.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat
import java.util.*

class CalculatorFragment : Fragment() {
    private val TAG = MainActivity::class.java.simpleName
    private val pattern = "hh:mm:ss"
    @SuppressLint("SimpleDateFormat")
    private val simpleDateFormat = SimpleDateFormat(pattern)
    private var date = simpleDateFormat.format(Date())
    private val symbols = listOf('+', '-', '*','/')
    private val history = mutableListOf(Operation("1+1","2"))
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        viewModel.display.let {
            view.text_visor.text = it
        }
        ButterKnife.bind(this, view)

        list_historic?.layoutManager = LinearLayoutManager(context)
        list_historic?.adapter = HistoryAdapter(context!!, R.layout.item_expression, history)

        return view
    }

    @Optional 
    @OnClick(R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
        R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9)
    fun onClickSymbol(view: View) {
//        val symbol = view.tag.toString()
//
//        Log.i(TAG,"Click no botão $symbol")
//        if(text_visor.text == "0") {
//            text_visor.text = symbol
//        }else {
//            text_visor.append(symbol)
//        }
        text_visor.text = viewModel.onClickSymbol(view.tag.toString())
    }

    @OnClick(R.id.button_equals)
    fun onClickEquals (view: View) {

        text_visor.text = viewModel.onClickEquals()
//        var text = text_visor.text.toString()
//        Log.i(TAG,"Click no botão =")
//
//        if (text_visor.text.isEmpty()) {
//            text_visor.text = "0"
//        }else if(text_visor.text[text_visor.text.length -1] in symbols){
//            text = text_visor.text.substring(0, text_visor.text.length - 1 )
//        }
//
//        if(text_visor.text.isNotEmpty() && text_visor.text.contains(Regex("-? *?/?^?.?"))) {
//            val expression = ExpressionBuilder(text).build()
//            text_visor.text = expression.evaluate().toString()
//            history.add(Operation(text,  text_visor.text.toString()))
//
//            list_historic?.adapter = HistoryAdapter(context!!, R.layout.item_expression, history)
//            historyAdapter.notifyDataSetChanged()
//            Log.i(TAG, "Operation is  ${text_visor.text}")
//        }
//        Log.i(TAG, "O resultado da expressão é ${Operation(text, text_visor.text.toString()).toString()}")
//
//        date = simpleDateFormat.format(Date())
////        Toast.makeText(this, "Calculando resultado ${date}", Toast.LENGTH_SHORT).show()

    }

    @OnClick(R.id.button_dot)
    fun onClickDot(view: View) {
        Log.i(TAG,"Click no botão .")
        if(text_visor.text.isNotEmpty() && text_visor.text[text_visor.text.length -1] !in symbols && !text_visor.text.endsWith(".") ) {
            text_visor.append(".")
        }
    }

    @OnClick(R.id.button_adition, R.id.button_subtract, R.id.button_multiply, R.id.button_divide)
    fun onClickArithmeticSymbol (view: View) {
        val symbol = view.tag.toString()
        Log.i(TAG,"Click no botão $symbol")
        if(text_visor.text.isNotEmpty() && text_visor.text[text_visor.text.length -1] !in symbols) {
            text_visor.append(symbol)
        }else if (text_visor.text.isNotEmpty() && text_visor.text[text_visor.text.length -1] in symbols) {
            val aux = text_visor.text.substring(0,text_visor.text.length - 1) + symbol
            text_visor.text = aux
        }
    }

    @OnClick(R.id.button_clear, R.id.button_clearAll)
    fun onClickClear(view: View) {
        val symbol = view.tag.toString()
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
//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    }

    @Optional
    @OnClick(R.id.button_hist)
    fun onClickHistory(view: View) {

    }

}
