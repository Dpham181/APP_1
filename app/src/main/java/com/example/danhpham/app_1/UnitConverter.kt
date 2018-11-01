package com.example.danhpham.app_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_unit_converter.*

val units = arrayOf(
    "Celsius",
    "Kelvin",
    "Fahrenheit"
)
 fun check(checkcasea: String? ,checkcaseb: String? , a:Double? ): Double? {
   if(checkcasea =="Celsius" ){
       if(checkcaseb == "Kelvin")
        return (a!! + 273.15)
       else if(checkcaseb == "Fahrenheit")
           return ((a!! * 9 / 5) + 32)

   }
   return a;
}
class UnitConverter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit_converter)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, units)

        inputListView.adapter = adapter
        outputListView.adapter = adapter

        inputListView.setOnItemClickListener { _, _, position, _ ->
            val itemSelectedA= units[position]
            outputListView.setOnItemClickListener { _, _, position, _ ->
                val itemSelectedB= units[position]
                val input = inputEditText.text
                if(input.isNotEmpty()) {
                    val result= check(itemSelectedA, itemSelectedB, java.lang.Double.valueOf(input.toString()))
                    resultTextView.text = result.toString();
                }
            }
        }


    }
}


