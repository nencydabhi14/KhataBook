package com.example.khatabook

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.khatabook.databinding.ActivityEditDeletOutScreenBinding
import java.text.SimpleDateFormat
import java.util.*

class Edit_Delet_OUT_Screen : AppCompatActivity() {

    lateinit var binding: ActivityEditDeletOutScreenBinding
    lateinit var id: String
    lateinit var name: String
    lateinit var number: String
    lateinit var amount: String
    lateinit var data: String
    lateinit var date: String
    lateinit var pay_mode: String


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditDeletOutScreenBinding.inflate(layoutInflater)
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
        val pay = intent.getStringExtra("mode")

        binding.numTxt.text = number
        binding.nameTxt.text = name
        binding.modeTxt.text = pay
        binding.amountTxtTryEx.text = amount
        binding.detailsTxtTryEx.text = data

//        if (date==null){

        binding.dateTxtTryEx.text = date

//        }
//        else
        {
        }

        binding.deleteData.setOnClickListener {

            DBHelper(this).DelateData(id.toString())
            Toast.makeText(this, "Delete Successfully..!", Toast.LENGTH_LONG).show()

            finish()
        }

        binding.callImgOut.setOnClickListener {
            val no = "tel:$number"
            val i = Intent(Intent.ACTION_CALL)
            i.data = Uri.parse(no)
            startActivity(i)
        }

        binding.updateDataOut.setOnClickListener {

            updateDialog(position)
        }

        binding.backExpence.setOnClickListener {
            finish()
        }
    }

    fun updateDialog(position: String?) {
        var dialog = Dialog(this)
        dialog.setContentView(R.layout.item_dialod)
        dialog.show()

        var radio_grup_d = dialog.findViewById<RadioGroup>(R.id.radio_grup_d)

        var customer_name_edt_d = dialog.findViewById<EditText>(R.id.customer_name_edt_d)
        var customer_no_edt_d = dialog.findViewById<EditText>(R.id.customer_no_edt_d)
        var amount_edt_d = dialog.findViewById<EditText>(R.id.amount_edt_d)
        var details_edt_d = dialog.findViewById<EditText>(R.id.details_edt_d)
        var date_edt_d = dialog.findViewById<EditText>(R.id.date_edt_d)
        var submit_btn_d = dialog.findViewById<RelativeLayout>(R.id.submit_btn_d)
        val name = Editable.Factory.getInstance().newEditable(name)
        val num = Editable.Factory.getInstance().newEditable(number)
        val amount = Editable.Factory.getInstance().newEditable(amount)
        val details = Editable.Factory.getInstance().newEditable(data)
        val date = Editable.Factory.getInstance().newEditable(date)

        customer_name_edt_d.text = name
        customer_no_edt_d.text = num
        amount_edt_d.text = amount
        details_edt_d.text = details
        date_edt_d.text = date

        submit_btn_d.setOnClickListener {

            if (radio_grup_d.checkedRadioButtonId == R.id.cash_d) {
                pay_mode = "cash"
            } else if (radio_grup_d.checkedRadioButtonId == R.id.online_d) {
                pay_mode = "online"
            }

            var calendar = Calendar.getInstance()
            var simpleDateFormat = SimpleDateFormat("KK:mm aaa ")
            var dateTime = simpleDateFormat.format(calendar.time).toString()

            DBHelper(this).UpdateData(
                id,
                amount_edt_d.text.toString(),
                details_edt_d.text.toString(),
                date_edt_d.text.toString(),
                1,
                customer_name_edt_d.text.toString(),
                customer_no_edt_d.text.toString(),
                pay_mode, dateTime
            )
            binding.numTxt.text = customer_no_edt_d.text.toString()
            binding.nameTxt.text = customer_name_edt_d.text.toString()
            binding.amountTxtTryEx.text = amount_edt_d.text.toString()
            binding.detailsTxtTryEx.text = details_edt_d.text.toString()
            binding.dateTxtTryEx.text = date_edt_d.text.toString()

            dialog.dismiss()

            Toast.makeText(this, "UpDate Successfully..!", Toast.LENGTH_LONG).show()
        }
    }
}