package com.example.khatabook

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.media.audiofx.BassBoost
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khatabook.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var binding11: ActivityMainBinding
    }

//    lateinit var dateTime: String
//    lateinit var calendar: Calendar
//    lateinit var simpleDateFormat: SimpleDateFormat


    var incomeTotal = 0
    var expTotal = 0
    private var size: Int = 0
    var list = arrayListOf<ModelData>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding11 = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding11.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)

        binding11.incomeBtn.setOnClickListener {
            var intent = Intent(this, Income_Form::class.java)
            startActivity(intent)
        }

        binding11.expenseBtn.setOnClickListener {
            var intent = Intent(this, Expence_Form::class.java)
            startActivity(intent)
        }
        binding11.filterImg.setOnClickListener {
            var intent = Intent(this, Filter_Screen::class.java)
            startActivity(intent)
        }
    }

    fun isAutoTimeEnabled(activity: Activity) =
        Settings.Global.getInt(activity.contentResolver, Settings.Global.AUTO_TIME) == 1

    fun isAutoTimeZoneEnabled(activity: Activity) =
        Settings.Global.getInt(activity.contentResolver, Settings.Global.AUTO_TIME_ZONE) == 1


    fun setUpRV(l1: ArrayList<ModelData>) {
        var adaptor = Income_Adaptor(this, l1)
        var lm = LinearLayoutManager(this)
        binding11.recyclerHome.layoutManager = lm
        binding11.recyclerHome.adapter = adaptor
    }

    @SuppressLint("ResourceAsColor")
    fun incomeExpenceCount(list: ArrayList<ModelData>) {
        var i = 0
        while (i < list.size) {
            if (list[i].statuses.equals("0")) {
                incomeTotal = incomeTotal + list[i].amount.toInt()
            } else {
                expTotal = expTotal + list[i].amount.toInt()
            }
            i++;
        }
        binding11.txtIncome.text = incomeTotal.toString()
        binding11.totalIncome.text = incomeTotal.toString()
        binding11.totalExpense.text = expTotal.toString()

        if (expTotal == 0) {
            binding11.txtExp.text = "0"
        } else {
            binding11.txtExp.text = (incomeTotal - expTotal).toString()
        }
        if (incomeTotal > expTotal) {
            binding11.todayIncome.setImageResource(R.drawable.rupeeincome)
            binding11.txtExp.setTextColor(Color.parseColor("#0F814D"))
        } else if (incomeTotal < expTotal) {
            binding11.todayIncome.setImageResource(R.drawable.rupeeexpence)
            binding11.txtExp.setTextColor(Color.parseColor("#DF1837"))
        }

        if (list.size.equals(0)) {
            binding11.waitLoder.isVisible = true
            binding11.bottomArrow.isVisible = true
        } else {
            binding11.bottomArrow.isVisible = false
            binding11.waitLoder.isVisible = false
        }

    }

    override fun onResume() {
        super.onResume()
        incomeTotal = 0
        expTotal = 0
        var db = DBHelper(this)

        list = db.ReadData()
        size = list.size
        try {
            incomeExpenceCount(list)
        } catch (e: Exception) {
        }
        setUpRV(list)
    }
}