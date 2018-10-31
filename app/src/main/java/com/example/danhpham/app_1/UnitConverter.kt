package com.example.danhpham.app_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.activity_unit_converter.*

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
        inputListView.setOnItemClickListener { _, _, position, _ ->

        }

        outputListView.adapter = adapter
        outputListView.setOnItemClickListener { _, _, position, _ ->

        }
    }
}
