package com.example.acalculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_expression.view.*

//class HistoryAdapter(context: Context, private val layout: Int, items: MutableList<Operation>) : ArrayAdapter<Operation>(context, layout, items) {
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val view = convertView ?: LayoutInflater.from(context).inflate(layout, parent, false)
//        val expressionParts = getItem(position)
//        view.text_expression.text = expressionParts?.expressao
//        view.text_result.text = "= ${expressionParts?.resultado}"
//        return view
//    }
//}
class HistoryAdapter(private val context: Context, private val layout: Int, private val items: MutableList<Operation>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val expression: TextView = view.text_expression
        val result: TextView = view.text_result
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(LayoutInflater.from(context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.expression.text = items[position].expressao
        holder.result.text = items[position].resultado
    }

    override fun getItemCount(): Int {
        return items.size
    }

}