package com.example.acalculator.MVVM

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import com.example.acalculator.HistoryAdapter
import com.example.acalculator.MainActivity
import com.example.acalculator.Operation
import com.example.acalculator.R
import kotlinx.android.synthetic.main.activity_history.list_historic
import kotlinx.android.synthetic.main.fragment_calculator.*

class CalculatorFragment() : Fragment(), OnDisplayChanged, Parcelable {
    private val TAG = MainActivity::class.java.simpleName

    private var history = mutableListOf(Operation("1+1", "2"),Operation("7+3", "10"))
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var viewModel: CalculatorViewModel

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)

        ButterKnife.bind(this, view)
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

//    @Optional
//    @OnClick(R.id.button_hist)
//    fun onClickHistory(view: View) {
//
//    }

    override fun onStart() {
        list_historic?.layoutManager = LinearLayoutManager(context)
        list_historic?.adapter = HistoryAdapter(context!!, R.layout.item_expression, history)
        viewModel.registerListener(this)
        super.onStart()
    }

    override fun onDisplayChanged(value: String?) {
        value?.let { text_visor.text = it }
    }

    override fun onHistoryChanged(values: MutableList<Operation>) {
        values?.let { history = it }
    }


    override fun onDestroy() {
        viewModel.unregisterListener()
        super.onDestroy()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CalculatorFragment> {
        override fun createFromParcel(parcel: Parcel): CalculatorFragment {
            return CalculatorFragment(parcel)
        }

        override fun newArray(size: Int): Array<CalculatorFragment?> {
            return arrayOfNulls(size)
        }
    }

}
