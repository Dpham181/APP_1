package com.example.danhpham.app_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.example.danhpham.app_1.R.id.*

import kotlinx.android.synthetic.main.activity_unit_converter.*
import kotlinx.android.synthetic.main.activity_unit_converter.view.*

val units = arrayOf(
    "Celsius",
    "Kelvin",
    "Fahrenheit"
)

class UnitConverter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit_converter)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, units)

        inputListView.adapter = adapter
        outputListView.adapter = adapter

        inputListView.setOnItemClickListener { _, _, position, _ ->

            when (units[position]) {
                "Celsius" -> outputListView.convertFromCelsius(resultTextView, inputEditText, outputListView)
                "Kelvin" -> outputListView.convertFromKelvin()
                "Fahrenheit" -> outputListView.convertFromFahrenheit()
            }
        }
    }
}

private fun ListView.convertFromCelsius(result: TextView?, input: EditText?, outputList: ListView?) {
    outputList!!.setOnItemClickListener { _, _, position, _ ->
        val start = java.lang.Double.valueOf(input!!.text.toString())
        when (units[position]) {
            "Celsius" -> result!!.text = input.text
            "Kelvin" -> result!!.celsiusToKelvin(start, result, input)
            "Fahrenheit" -> result!!.celsiusToFahrenheit(start, result)
        }
    }
}

private fun ListView.convertFromKelvin() {

}

private fun ListView.convertFromFahrenheit() {

}

private fun TextView.celsiusToKelvin(initialUnit: Double?, result: TextView?, input: EditText?) {
    val convertedUnit = initialUnit!! + 273.15
    result!!.text = convertedUnit.toString()
    val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val notEmpty: TextView.() -> Boolean = { text.isNotEmpty() }
            if (notEmpty(input!!)) {
                val originalUnit = java.lang.Double.valueOf(input.text.toString())
                val convertUnit = originalUnit + 273.15
                result.text = convertUnit.toString()
            }
        }
    }
    input!!.addTextChangedListener(textChangeListener)

}

private fun TextView.celsiusToFahrenheit(start : Double?, result: TextView?) {
    val end = (start!! * 9/5) + 32
    result!!.text = end.toString()
}

private fun textWatcherManager() {

}

