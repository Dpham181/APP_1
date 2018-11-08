package com.example.danhpham.app_1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_tax.*



internal fun cal(cost: Double?, tax: Double?): Double? {

    return (cost!! * tax!!)/100
}


class Tax : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val tag = "Lifecycle of Tax"
    // lifecycle check
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tax)
        Log.d(tag , " onCreate()");

    // two edittext and textwacher to onchange cal
        fun EditText.setWatcher() {
            this.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val input1 = cost_input.text
                    val input2 = tax_input.text
                    if(input1.isNotEmpty() && input2.isNotEmpty()){
                        val finalCal = cal(java.lang.Double.valueOf(cost_input.text.toString()), java.lang.Double.valueOf(tax_input.text.toString()))
                        val finalcost= finalCal!! + java.lang.Double.valueOf(cost_input.text.toString())
                        val ftax = "$" + finalCal.toString()
                        val fcost = "$" + finalcost.toString()
                        output_tax.text = ftax
                        output_cost.text= fcost
                    }
                    else{
                        output_tax.text = "0"
                        output_cost.text= "0"

                    }                }

            })
        }

        tax_input.setWatcher()
        cost_input.setWatcher()

    }

    override fun onDestroy()
    {
        super.onDestroy();
        Log.d(tag, " onDestroy() ");

    }

}
