package com.example.khatabook

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khatabook.databinding.ActivityFilterScreenBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS


class Filter_Screen : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    companion object {
        lateinit var binding1: ActivityFilterScreenBinding
        var list = arrayListOf<ModelData>()
        private var size: Int = 0
    }

    var type: String = ""
    var date: String = ""
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var currentDay = 0
    var currentMonth: Int = 0
    var currentYear: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding1 = ActivityFilterScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding1.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)

        var c = Calendar.getInstance()
        var simple = SimpleDateFormat("dd/MM/yyyy")
        var datecurrent = simple.format(c.time).toString()

        var calendar = Calendar.getInstance()
        var simpleDateFormat = SimpleDateFormat("KK:mm aaa ")
        var dateTime = simpleDateFormat.format(calendar.time).toString()


//        val today = Calendar.getInstance()
//        val twoDaysAgo = today.clone() as Calendar
//        twoDaysAgo.add(Calendar.DATE, -2)
//        val twoDaysLater = today.clone() as Calendar
//        twoDaysLater.add(Calendar.DATE, 2)
//        binding1.datePicker.setMinDate(twoDaysAgo.timeInMillis)
////        binding1.datePicker(twoDaysLater.timeInMillis)


        binding1.dateFilterScreen.text = datecurrent
        binding1.timeFilterScreen.text = dateTime

        binding1.logo.setOnClickListener {
            finish()
        }

        binding1.filter.setOnClickListener {
            var dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.item_filter_design)
            dialog.show()

            var filter_grp = dialog.findViewById<RadioGroup>(R.id.filter_grp)
            var done_txt = dialog.findViewById<TextView>(R.id.done_txt)
            var date_filter = dialog.findViewById<RadioButton>(R.id.date_filter)

            date_filter?.setOnClickListener {

                var dialog = Dialog(this)
                dialog.setContentView(R.layout.date_dailog)
                dialog.show()

                var date_edt_d = dialog.findViewById<EditText>(R.id.date_edt_d)
                var done_date = dialog.findViewById<Button>(R.id.done_date)
                done_date.setOnClickListener {
                    date = date_edt_d.text.toString()
                    dialog.dismiss()
                }
                dialog.dismiss()
            }


            done_txt!!.setOnClickListener {
                if (filter_grp!!.checkedRadioButtonId == R.id.income_filter) {
                    type = "income"
                    var db = DBHelper(this)

                    list = db.ReadDataQuery("1")
                    size = list.size

                    setUpRV(list)

                    binding1.datePicker.visibility = View.INVISIBLE


                } else if (filter_grp.checkedRadioButtonId == R.id.expense_filter) {
                    type = "expense"
                    var db = DBHelper(this)

                    list = db.ReadDataQuery("0")
                    size = list.size

                    setUpRV(list)

                    binding1.datePicker.visibility = View.INVISIBLE


                } else if (filter_grp.checkedRadioButtonId == R.id.all_filter) {
                    type = "All"
                    var db = DBHelper(this)

                    list = db.ReadData()
                    size = list.size

                    setUpRV(list)
                    binding1.datePicker.visibility = View.INVISIBLE


                }
                dialog.dismiss()

            }
        }
    }


    fun setUpRV(l1: ArrayList<ModelData>) {
        var adaptor = Filter_List_Adaptor(this, l1, type)
        var lm = LinearLayoutManager(this)
        binding1.filterRecycler.layoutManager = lm
        binding1.filterRecycler.adapter = adaptor
    }

    override fun onResume() {
        super.onResume()

        var db = DBHelper(this)

        list = db.ReadData()
        size = list.size

        setUpRV(list)
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
//        currentDay = p3
//        currentYear = p1
//        currentMonth = p2 + 1
//
//        var date = (String.format("%02d", currentDay) + "/" + String.format("%02d", currentMonth) + "/" + currentYear)
//
//        Toast.makeText(this, "" + "$date", Toast.LENGTH_SHORT).show()
//        Log.e("TAG", "onDateSet: ================= $date" )

//        var db = DBHelper(this)
//
//        list = db.ReadDataDate(date)
//        size = list.size
//
//        setUpRV(list)
    }

}