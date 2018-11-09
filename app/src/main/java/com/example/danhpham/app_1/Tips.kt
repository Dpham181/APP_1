package com.example.danhpham.app_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_tips.*
import kotlinx.android.synthetic.main.activity_main.*


val tag = "Lifecycle of tip"

// function for cal
internal fun Tipcal ( bill: Double?, tip: Double?) :Double? {
    return ((bill!! * tip!!)/100 )+ bill
}
// function for show and hide text
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
        Log.d(tag , " onCreate()");

        // set all the array to match with the text for using the show and hide function

        val setBetween = arrayOf(bt_label, between_input)
        val setTotal = arrayOf( textView6,textView7, TA, TP)
        val setPerson = arrayOf(textView8, textView9, textView10, PTA, PTP)
        val setInvisible = setBetween + setPerson + setTotal
        for (widget  in setInvisible) {
            widget.setVisibility()

        }
        // checking two input if empty or not then cal tostring the output for amount
        fun TextView.AmountOuput() {

            val bill = bill_input.text
            val tip = tip_input.text

            if (bill.isNotEmpty() && tip.isNotEmpty()) {
                val total = Tipcal(java.lang.Double.valueOf(bill.toString()), java.lang.Double.valueOf(tip.toString()))
                this.text = total.toString()
            } else
                this.text = "0"

        }
        // checking two input if empty or not then cal tostring the output for tip

        fun TextView.tipouput() {
            val bill = bill_input.text
            val tip = tip_input.text

            if (bill.isNotEmpty() && tip.isNotEmpty()) {
                val Tip = Tipcal(java.lang.Double.valueOf(bill.toString()), java.lang.Double.valueOf(tip.toString()))
                val finalTip = Tip!! - java.lang.Double.valueOf(bill.toString())
                this.text = finalTip.toString()
            } else
                this.text = "0"

        }
        // function for checkbox if checked then show the slip input otherwise hiding as default.
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                bt_label.setVisibility()
                between_input.setVisibility()


            } else {
                bt_label.setVisibility()
                between_input.setVisibility()


            }
        }
        // buuton for calcuation final output with hiding function
        // show and hide final output if slip or not
        calculator.setOnClickListener {

            TA.tipouput()
            TP.AmountOuput()
            if(bt_label.visibility == VISIBLE){

                for(widget in setPerson){
                    widget.visibility = VISIBLE
                }
                for(widget in setTotal){
                    widget.visibility = VISIBLE
                }
                val slipTipAmount= TA.text

                val slipAmount= TP.text
                val slipByPerson = between_input.text
                if(slipAmount.isNotEmpty() and slipByPerson.isNotEmpty() and slipTipAmount.isNotEmpty()){
                    val personTipTotal = java.lang.Double.valueOf(slipTipAmount.toString()) / java.lang.Double.valueOf(slipByPerson.toString())
                    PTA.text = personTipTotal.toString()
                    val personAmountTotal = java.lang.Double.valueOf(slipAmount.toString()) / java.lang.Double.valueOf(slipByPerson.toString())
                    PTP.text = personAmountTotal.toString()
                }
            }
            else if(bt_label.visibility == INVISIBLE)
            {
                for(widget in setPerson){
                    widget.visibility = INVISIBLE
                }
                for(widget in setTotal){
                    widget.visibility = VISIBLE
                }
            }

        }


    }
    

    override fun onDestroy()
    {
        super.onDestroy();
        Log.d(tag , " onDestroy() ");
    }
    }





