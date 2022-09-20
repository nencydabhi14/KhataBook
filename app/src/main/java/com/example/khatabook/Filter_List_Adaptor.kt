package com.example.khatabook

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.khatabook.Filter_Screen.Companion.binding1
import com.example.khatabook.Filter_Screen.Companion.list

class Filter_List_Adaptor(
    val filterScreen: Filter_Screen,
    val l1: ArrayList<ModelData>,
    val type: String
) :
    RecyclerView.Adapter<Filter_List_Adaptor.ViewData>() {

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var amount_txt_filter = itemView.findViewById<TextView>(R.id.amount_txt_filter)
        var date_txt_filter = itemView.findViewById<TextView>(R.id.date_txt_filter)
        var time_txt_filter = itemView.findViewById<TextView>(R.id.time_txt_filter)
        var rupee_filter = itemView.findViewById<ImageView>(R.id.rupee_filter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view =
            LayoutInflater.from(filterScreen).inflate(R.layout.filter_design_file, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {


        holder.amount_txt_filter.text = l1[position].amount
        holder.date_txt_filter.text = l1[position].date
        holder.time_txt_filter.text = l1[position].dattime

        if (list[position].statuses.equals("0")){
            holder.amount_txt_filter.setTextColor(Color.parseColor("#0F814D"))
            holder.rupee_filter.setImageResource(R.drawable.rupeeincome)
        }else{
            holder.amount_txt_filter.setTextColor(Color.parseColor("#DF1837"))
            holder.rupee_filter.setImageResource(R.drawable.rupeeexpence)
        }
        if (type.equals("income")) {
            holder.amount_txt_filter.setTextColor(Color.parseColor("#0F814D"))
            holder.rupee_filter.setImageResource(R.drawable.rupeeincome)
        }
        if (type.equals("expense")) {
            holder.amount_txt_filter.setTextColor(Color.parseColor("#DF1837"))
            holder.rupee_filter.setImageResource(R.drawable.rupeeexpence)
        }
    }

    override fun getItemCount(): Int {
        return l1.size
    }
}