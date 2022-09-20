package com.example.khatabook

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khatabook.databinding.ActivityIncomeFormBinding
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class Income_Form : AppCompatActivity() {


    companion object {
        lateinit var binding2: ActivityIncomeFormBinding
        lateinit var mode: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding2 = ActivityIncomeFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding2.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.ofwhite)
        var db = DBHelper(this)


//        binding.buttonDate1.setOnClickListener {
//
//            val c = Calendar.getInstance()
//            val year = c.get(Calendar.YEAR)
//            val month = c.get(Calendar.MONTH)
//            val day = c.get(Calendar.DAY_OF_MONTH)
//
//            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//
//                // Display Selected date in textbox
//                binding.textViewDate1.setText("" + dayOfMonth + " " + MONTHS + ", " + year)
//
//            }, day, month, year)
//
//            dpd.show()
//
//
//
//
//            binding.textViewDate1!!.text = "--/--/----"
//
//            // create an OnDateSetListener
//            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
//                override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
//                                       dayOfMonth: Int) {
//                    cal.set(Calendar.YEAR, year)
//                    cal.set(Calendar.MONTH, monthOfYear)
//                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//                    updateDateInView()
//                }
//            }
//
//            // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
//            binding.buttonDate1!!.setOnClickListener(object : View.OnClickListener {
//                override fun onClick(view: View) {
//                    DatePickerDialog(this@Income_Form,
//                        dateSetListener,
//                        // set DatePickerDialog to point to today's date when it loads up
//                        cal.get(Calendar.YEAR),
//                        cal.get(Calendar.MONTH),
//                        cal.get(Calendar.DAY_OF_MONTH)).show() } })
//        }

        binding2.saveBtn.setOnClickListener {

            if (binding2.cusNameEdt.text.toString().equals("")) {
                binding2.cusNameEdt.setError("Enter Customer Name")
            }
            if (binding2.cusNumEdt.text.toString().equals("")) {
                binding2.cusNumEdt.setError("Enter Customer number")
            }
            if (binding2.logMailEdt.text.toString().equals("")) {
                binding2.logMailEdt.setError("Enter Amount")
            }
            if (binding2.detailsEdt.text.toString().equals("")) {
                binding2.detailsEdt.setError("Enter details")
            }
            if (binding2.dateEdt.text.toString().equals("")) {
                binding2.dateEdt.setError("Enter date")
            }

            else {
                if (binding2.radioGrup.checkedRadioButtonId == R.id.cash) {
                    mode = "cash"
                } else if (binding2.radioGrup.checkedRadioButtonId == R.id.online) {
                    mode = "online"
                }

                var calendar = Calendar.getInstance()
                var simpleDateFormat = SimpleDateFormat("KK:mm aaa ")
                var dateTime = simpleDateFormat.format(calendar.time).toString()

                db.insertData(
                    binding2.logMailEdt.text.toString(),
                    binding2.detailsEdt.text.toString(),
                    binding2.dateEdt.text.toString(), 0,
                    binding2.cusNameEdt.text.toString(),
                    binding2.cusNumEdt.text.toString(), mode, dateTime
                )
                finish()
            }
        }

        binding2.backin.setOnClickListener {
            finish()
        }
    }

//    private fun updateDateInView() {
//        val myFormat = "MM/dd/yyyy" // mention the format you need
//        val sdf = SimpleDateFormat(myFormat, Locale.US)
//        binding.textViewDate1!!.text = sdf.format(cal.getTime())
//    }


}