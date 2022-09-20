package com.example.khatabook

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khatabook.MainActivity.Companion.binding11
import com.example.khatabook.databinding.ActivityExpenceFormBinding
import java.text.SimpleDateFormat
import java.util.*

class Expence_Form : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    lateinit var binding: ActivityExpenceFormBinding
    lateinit var mode: String
//    var day = 0
//    var month: Int = 0
//    var year: Int = 0
//    var currentDay = 0
//    var currentMonth: Int = 0
//    var currentYear: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityExpenceFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var db = DBHelper(this)

        window.statusBarColor = ContextCompat.getColor(this, R.color.ofwhite)

//        binding.dateEdtEx.setOnClickListener {
//            val calendar: Calendar = Calendar.getInstance()
//            day = calendar.get(Calendar.DAY_OF_MONTH)
//            month = calendar.get(Calendar.MONTH)
//            year = calendar.get(Calendar.YEAR)
//            val datePickerDialog =
//                DatePickerDialog(this, this, year, month, day)
//            datePickerDialog.show()
//        }

        binding.saveBtnOut.setOnClickListener {

            if (binding.customerNameEdt.text.toString().equals("")) {
                binding.customerNameEdt.setError("Enter Customer name")
            }
            if (binding.customerContectNumber.text.toString().equals("")) {
                binding.customerContectNumber.setError("Enter Customer number")
            }
            if (binding.amountOutEdt.text.toString().equals("")) {
                binding.amountOutEdt.setError("Enter Amount")
            }
            if (binding.detailsEdtOut.text.toString().equals("")) {
                binding.detailsEdtOut.setError("Enter details")
            }
            if (binding.dateEdtEx.text.toString().equals("")) {
                binding.dateEdtEx.setError("Enter date")
            } else {
                if (binding.radioGrupEx.checkedRadioButtonId == R.id.cash_ex) {
                    mode = "cash"
                } else if (binding.radioGrupEx.checkedRadioButtonId == R.id.online_ex) {
                    mode = "online"
                }


                var calendar = Calendar.getInstance()
                var simpleDateFormat = SimpleDateFormat("KK:mm aaa ")
                var dateTime = simpleDateFormat.format(calendar.time).toString()

                db.insertData(
                    binding.amountOutEdt.text.toString(),
                    binding.detailsEdtOut.text.toString(),
                    binding.dateEdtEx.text.toString(),1,
                    binding.customerNameEdt.text.toString(),
                    binding.customerContectNumber.text.toString(),mode,dateTime
                )
                finish()
            }

        }
        binding.backout.setOnClickListener {
            finish()
        }
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }

//    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
//        currentDay = p3
//        currentYear = p1
//        currentMonth = p2 + 1
//
//        var date = (String.format("%02d", currentDay) + "/" + String.format("%02d", currentMonth) + "/" + currentYear)
//
//        Toast.makeText(this, "" + "$date", Toast.LENGTH_SHORT).show()
//        Log.e("TAG", "onDateSet: ================= $date" )
//
//    }
}