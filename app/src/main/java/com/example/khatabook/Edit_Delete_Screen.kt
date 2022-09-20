package com.example.khatabook

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.khatabook.databinding.ActivityEditDeleteScreenBinding
import java.text.SimpleDateFormat
import java.util.*

class Edit_Delete_Screen : AppCompatActivity() {

    lateinit var binding: ActivityEditDeleteScreenBinding
    lateinit var id: String
    lateinit var name: String
    lateinit var number: String
    lateinit var amount: String
    lateinit var data: String
    lateinit var date: String
    lateinit var pay: String
    lateinit var pay_mode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditDeleteScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)


        val position = intent.getStringExtra("ps")
        id = intent.getStringExtra("id").toString()
        amount = intent.getStringExtra("amount").toString()
        data = intent.getStringExtra("details").toString()
        date = intent.getStringExtra("date").toString()
        name = intent.getStringExtra("name").toString()
        number = intent.getStringExtra("number").toString()
        pay = intent.getStringExtra("mode").toString()

        binding.amountDetailsTxt.text = amount
        binding.detailsDetailsTxt.text = data
        binding.dateIncomeDetails.text = date


//        if (date==null){
//        }
//        else
//        {
//        }
        binding.detailsNameTxt.text = name
        binding.detailsNumTxt.text = number
        binding.payMode.text = pay


        binding.deleteDataExpence.setOnClickListener {

            DBHelper(this).DelateData(id.toString())
            Toast.makeText(this, "Delete Successfully..!", Toast.LENGTH_LONG).show()

            finish()
        }

        binding.updateData.setOnClickListener {
            updateDialog(position)
        }

        binding.callImg.setOnClickListener {
            val no = "tel:$number"
            val i = Intent(Intent.ACTION_CALL)
            i.data = Uri.parse(no)
            startActivity(i)
        }

        binding.backDetails.setOnClickListener {
            finish()
        }
    }

    fun updateDialog(position: String?) {
        var dialog = Dialog(this)
        dialog.setContentView(R.layout.item_dialod_income)
        dialog.show()

        var radio_grup_i = dialog.findViewById<RadioGroup>(R.id.radio_grup_i)
        var customer_name_edt_i = dialog.findViewById<EditText>(R.id.customer_name_edt_i)
        var customer_no_edt_i = dialog.findViewById<EditText>(R.id.customer_no_edt_i)
        var amount_edt_i = dialog.findViewById<EditText>(R.id.amount_edt_i)
        var details_edt_i = dialog.findViewById<EditText>(R.id.details_edt_i)
        var date_edt_i = dialog.findViewById<EditText>(R.id.date_edt_i)
        var submit_btn_i = dialog.findViewById<RelativeLayout>(R.id.submit_btn_i)
        val name = Editable.Factory.getInstance().newEditable(name)
        val num = Editable.Factory.getInstance().newEditable(number)
        val amount = Editable.Factory.getInstance().newEditable(amount)
        val details = Editable.Factory.getInstance().newEditable(data)
        val date = Editable.Factory.getInstance().newEditable(date)
        val pay = Editable.Factory.getInstance().newEditable(pay)


        customer_name_edt_i.text = name
        customer_no_edt_i.text = num
        amount_edt_i.text = amount
        details_edt_i.text = details
        date_edt_i.text = date

//        if (radio_grup_i.checkedRadioButtonId == R.id.cash_i) {
//            pay_mode = "$pay"
//        } else if (radio_grup_i.checkedRadioButtonId == R.id.online_i) {
//            pay_mode = "$pay"
//        }

        submit_btn_i.setOnClickListener {

            if (radio_grup_i.checkedRadioButtonId == R.id.cash_i) {
                pay_mode = "cash"
            } else if (radio_grup_i.checkedRadioButtonId == R.id.online_i) {
                pay_mode = "online"
            }
            var calendar = Calendar.getInstance()
            var simpleDateFormat = SimpleDateFormat("KK:mm aaa ")
            var dateTime = simpleDateFormat.format(calendar.time).toString()

            DBHelper(this).UpdateData(
                id,
                amount_edt_i.text.toString(),
                details_edt_i.text.toString(),
                date_edt_i.text.toString(),
                0,
                customer_name_edt_i.text.toString(),
                customer_no_edt_i.text.toString(),
                pay_mode,dateTime
            )


            binding.detailsNumTxt.text = customer_no_edt_i.text.toString()
            binding.detailsNameTxt.text = customer_name_edt_i.text.toString()
            binding.amountDetailsTxt.text = amount_edt_i.text.toString()
            binding.detailsDetailsTxt.text = details_edt_i.text.toString()
            binding.dateIncomeDetails.text = date_edt_i.text.toString()


//            var l1 = DBHelper(mainActivity).ReadData()
//            mainActivity.setUpRV(l1)

            dialog.dismiss()

            Toast.makeText(this, "UpDate Successfully..!", Toast.LENGTH_LONG).show()

        }

    }

}


