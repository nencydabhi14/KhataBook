package com.example.khatabook

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.khatabook.MainActivity.Companion.binding11

class Income_Adaptor(val incomeForm: MainActivity, val l1: ArrayList<ModelData>) :
    RecyclerView.Adapter<Income_Adaptor.ViewData>() {

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var amount_txt = itemView.findViewById<TextView>(R.id.amount_txt)
        var expence_txt = itemView.findViewById<TextView>(R.id.expence_txt)
        var details_txt = itemView.findViewById<TextView>(R.id.details_txt)
        var date_txt = itemView.findViewById<TextView>(R.id.date_txt)
        var details_card = itemView.findViewById<CardView>(R.id.details_card)
        var expense = itemView.findViewById<ImageView>(R.id.expense)
        var income = itemView.findViewById<ImageView>(R.id.income)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view = LayoutInflater.from(incomeForm).inflate(R.layout.design_file, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        if (l1[position].statuses.equals("0")) {
            holder.amount_txt.text = l1[position].amount
            holder.income.isVisible = true

            holder.details_card.setOnClickListener {
                var intent = Intent(incomeForm, Edit_Delete_Screen::class.java)
                intent.putExtra("ps",position)
                intent.putExtra("id", l1[position].id)
                intent.putExtra("amount", l1[position].amount)
                intent.putExtra("details", l1[position].data)
                intent.putExtra("date", l1[position].date)
                intent.putExtra("name", l1[position].cus_nama)
                intent.putExtra("number", l1[position].customer_num)
                intent.putExtra("mode", l1[position].paym)
                incomeForm.startActivity(intent)

//                Toast.makeText(
//                    incomeForm,""+l1[position].id + "\n" + l1[position].amount + "\n" + l1[position].data + "\n" + l1[position].date + "\n" + l1[position].cus_nama
//                            +"\n"+ l1[position].customer_num + "\n" + l1[position].paym,Toast.LENGTH_SHORT
//                ).show()
            }
        } else {
            holder.expence_txt.text = l1[position].amount
            holder.expense.isVisible = true

            holder.details_card.setOnClickListener {
                var intent = Intent(incomeForm, Edit_Delet_OUT_Screen::class.java)
                intent.putExtra("ps",position)
                intent.putExtra("id", l1[position].id)
                intent.putExtra("amount", l1[position].amount)
                intent.putExtra("details", l1[position].data)
                intent.putExtra("date", l1[position].date)
                intent.putExtra("name", l1[position].cus_nama)
                intent.putExtra("number", l1[position].customer_num)
                intent.putExtra("mode", l1[position].paym)
                incomeForm.startActivity(intent)

//                Toast.makeText(
//                    incomeForm,
//                    "" + l1[position].id + "\n" + l1[position].amount + "\n" + l1[position].data + "\n" + l1[position].date+ "\n" + l1[position].cus_nama
//                            +"\n"+ l1[position].customer_num + "\n" + l1[position].paym,
//                    Toast.LENGTH_SHORT
//                ).show()
            }

//            if (l1[position].statuses.equals("0")){
//                holder.amount_txt.text = l1[position].amount
//                holder.income.isVisible = true
//            }
//            else
//            {
//                holder.expence_txt.text = l1[position].amount
//                holder.expense.isVisible = true
//            }
        }

        holder.details_txt.text = l1[position].data
        holder.date_txt.text = l1[position].dattime

        binding11.totalTxt.text = l1.size.toString()

//        do {
//            binding11.tryTxt.text = l1[position].amount
//            l1[position].amount"++"
//
//        }while ()
    }
    override fun getItemCount(): Int {
        return l1.size
    }
}