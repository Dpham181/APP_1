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
import java.lang.NumberFormatException
import java.util.*

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
                "Kelvin" -> outputListView.convertFromKelvin(resultTextView, inputEditText, outputListView)
                "Fahrenheit" -> outputListView.convertFromFahrenheit(resultTextView, inputEditText, outputListView)
            }
        }
    }
}

private fun TextView.toTwoDec(num: Double?): String {
    return String.format(Locale.US, "%.2f", num)
}

private fun ListView.convertFromCelsius(result: TextView?, input: EditText?, outputList: ListView?) {
    outputList!!.setOnItemClickListener { _, _, position, _ ->
        try {
            val start = java.lang.Double.valueOf(input!!.text.toString())
            when (units[position]) {
                "Celsius" -> result!!.text = input.text
                "Kelvin" -> result!!.celsiusToKelvin(start, result, input)
                "Fahrenheit" -> result!!.celsiusToFahrenheit(start, result, input)
            }
        } catch (e: NumberFormatException) {
            return@setOnItemClickListener
        }
    }
}

private fun ListView.convertFromKelvin(result: TextView?, input: EditText?, outputList: ListView?) {
    outputList!!.setOnItemClickListener { _, _, position, _ ->
        try {
            val start = java.lang.Double.valueOf(input!!.text.toString())
            when (units[position]) {
                "Celsius" -> result!!.kelvinToCelsius(start, result, input)
                "Kelvin" -> result!!.text = input.text
                "Fahrenheit" -> result!!.kelvinToFahrenheit(start, result, input)
            }
        } catch (e: NumberFormatException) {
            return@setOnItemClickListener
        }
    }
}

private fun TextView.kelvinToFahrenheit(initialUnit: Double?, result: TextView?, input: EditText?) {
    textWatcherManager(
        result,
        input,
        initialUnit,
        ::convertKToF)
}

private fun TextView.kelvinToCelsius(initialUnit: Double?, result: TextView?, input: EditText?) {
    textWatcherManager(
        result,
        input,
        initialUnit,
        ::convertKToC)
}

private fun convertKToC(start : Double?, result: TextView?) {
    result!!.text = result.toTwoDec(start!! - 273.15)
}

private fun convertKToF(start : Double?, result: TextView?) {
    result!!.text = result.toTwoDec((start!! - 273.15) * 9/5 + 32)
}

private fun ListView.convertFromFahrenheit(result: TextView?, input: EditText?, outputList: ListView?) {
    outputList!!.setOnItemClickListener { _, _, position, _ ->
        try {
            val start = java.lang.Double.valueOf(input!!.text.toString())
            when (units[position]) {
                "Celsius" -> result!!.fahrenheitToCelsius(start, result, input)
                "Kelvin" -> result!!.fahrenheitToKelvin(start, result, input)
                "Fahrenheit" -> result!!.text = input.text
            }
        } catch (e: NumberFormatException) {
            return@setOnItemClickListener
        }
    }
}

private fun TextView.fahrenheitToKelvin(initialUnit: Double?, result: TextView?, input: EditText?) {
    textWatcherManager(
        result,
        input,
        initialUnit,
        ::convertFToK)
}

private fun convertFToK(start : Double?, result: TextView?) {
    result!!.text = result.toTwoDec((start!! - 32.0) * 5/9 + 273.15)
}

private fun TextView.fahrenheitToCelsius(initialUnit: Double?, result: TextView?, input: EditText?) {
    textWatcherManager(
        result,
        input,
        initialUnit,
        ::convertFtoC)
}

private fun convertFtoC(start : Double?, result: TextView?) {
    result!!.text = result.toTwoDec((start!! - 32.0) * 5/9)
}

private fun TextView.celsiusToKelvin(initialUnit: Double?, result: TextView?, input: EditText?) {
    textWatcherManager(
        result,
        input,
        initialUnit,
        ::convertCToK)
}

private fun convertCToK(start : Double?, result: TextView?) {
    result!!.text = result.toTwoDec(start!! + 273.15)
}

private fun TextView.celsiusToFahrenheit(initialUnit: Double?, result: TextView?, input: EditText?) {
    textWatcherManager(
        result,
        input,
        initialUnit,
        ::convertCToF)
}

private fun convertCToF(start : Double?, result: TextView?) {
    result!!.text = result.toTwoDec((start!! * 9/5) + 32.0)
}

private fun textWatcherManager(
    result: TextView?,
    input: EditText?,
    initialUnit: Double?,
    f: (initialUnit: Double?, result: TextView?) -> Unit
) {
    f.invoke(initialUnit, result)
    val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val notEmpty: TextView.() -> Boolean = { text.isNotEmpty() }
            if (notEmpty(input!!)) {
                try {
                    val originalUnit = java.lang.Double.valueOf(input.text.toString())
                    f.invoke(originalUnit, result)
                } catch (e: NumberFormatException) {
                    return
                }
            }
        }
    }
    input!!.addTextChangedListener(textChangeListener)
}

