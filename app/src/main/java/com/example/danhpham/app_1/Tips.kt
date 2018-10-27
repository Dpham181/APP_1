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


internal fun Tipcal ( bill: Double?, tip: Double?) :Double? {
    return ((bill!! * tip!!)/100 )+ bill
}
 fun View.setVisibility(){
     if(visibility == VISIBLE ){
         visibility = INVISIBLE
     }else
     {
         visibility = VISIBLE
     }
}
val state_status = arrayOf(
    "test"
)

class Tips : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)



        val setBetween = arrayOf(bt_label, between_input)
        val setTotal = arrayOf( textView6,textView7, TA, TP)
        val setPerson = arrayOf(textView8, textView9, textView10, PTA, PTP)
        val setInvisible = setBetween + setPerson + setTotal
        for (widget  in setInvisible) {
            widget.setVisibility()

        }

        fun TextView.AmountOuput() {

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
                val Tip = Tipcal(java.lang.Double.valueOf(bill.toString()), java.lang.Double.valueOf(tip.toString()))
                val finalTip = Tip!! - java.lang.Double.valueOf(bill.toString())
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

            TA.tipouput()
            TP.AmountOuput()
            if(bt_label.visibility == VISIBLE){

                for(widget in setPerson){
                    widget.setVisibility()
                }
                for(widget in setTotal){
                    widget.setVisibility()
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
            else
            {
                for(widget in setTotal){
                    widget.setVisibility()
                }

            }

        }


    }


    override fun  onStart()
    {
        super.onStart();
    }

    override fun onRestart()
    {
        super.onRestart();
        Log.d(tag , "onRestart()");
    }

    override fun onResume()
    {
        super.onResume();
        Log.d(tag , " onResume() ");
    }

    override fun onPause()
    {
        super.onPause();
        Log.d(tag , " onPause() ");
    }
    override fun onStop()
    {
        super.onStop();
        Log.d(tag , "the onStop() " );
    }

    override fun onDestroy()
    {
        super.onDestroy();
        Log.d(tag , " onDestroy() ");
    }
    }





