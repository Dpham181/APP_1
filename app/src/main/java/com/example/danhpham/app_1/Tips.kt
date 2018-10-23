package com.example.danhpham.app_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_tips.*


internal fun Tipcal(bill: Double?,tip: Double?) :Double? {

    return ((bill!! * tip!!)/100 )+ bill!!
}
fun View.setVisibility(){
     if(visibility == VISIBLE ){
         visibility = INVISIBLE
     }else
     {
         visibility = VISIBLE
     }
}


class Tips : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)


        val setInvisible = arrayOf(
            bt_label, between_input, textView6, textView7, textView8, textView9, textView10, TA, TP, PTA, PTP

        )
        for (widget in setInvisible) {
            widget.setVisibility()
        }

        fun TextView.ouput() {
            val bill = bill_input.text
            val tip = tip_input.text

            if (bill.isNotEmpty() && tip.isNotEmpty()) {
                val total = Tipcal(java.lang.Double.valueOf(bill.toString()), java.lang.Double.valueOf(tip.toString()))
                this.text = total.toString()
            } else
                this.text = "0"

        }

        fun TextView.tipouput() {
            val bill = bill_input.text
            val tip = tip_input.text

            if (bill.isNotEmpty() && tip.isNotEmpty()) {
                val tip = Tipcal(java.lang.Double.valueOf(bill.toString()), java.lang.Double.valueOf(tip.toString()))
                val finalTip = tip!! - java.lang.Double.valueOf(bill.toString())
                this.text = finalTip.toString()
            } else
                this.text = "0"

        }
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                bt_label.setVisibility()
                between_input.setVisibility()


            } else {
                bt_label.setVisibility()
                between_input.setVisibility()


            }
        }

        calculator.setOnClickListener {

        }


    }

    }





