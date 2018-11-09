package com.example.danhpham.app_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_bmi.*
import java.text.DecimalFormat


//for calculation of different units
val unitList = arrayOf(
    "Standard",
    "Metric"
)

//extension for the EditText to check if input is valid or not
internal fun EditText.checkValid(): Double {
    return if(this.text.isEmpty()){ //if empty, return 0
        0.0
    }else{
        java.lang.Double.valueOf(this.text.toString()) //else return the value as double
    }
}

//converts the number decimal place
private fun convertToDeciplace(`in`: Double): String {

    val df = DecimalFormat("#.#")
//return 0.0 if empty, otherwise format into decimal
    return if(`in`.isNaN()){
        df.format(0.0)
    } else {
        df.format(`in`)
    }
}
class BMI : AppCompatActivity() {

    //text watcher object override for standard units
    private val textListenerStandard = object:TextWatcher{
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            val heightMain = heightFeetIn.checkValid()
            val heightSecondary = heightInchesIn.checkValid() //check if in standard form and set to 0/uneditable if meters is selected
            val weightMain = weightIn.checkValid()

            //apply calculation based on unit system selected
            val resultNum = bmiCalcStandard(heightMain.toInt(), heightSecondary.toInt(), weightMain.toInt()) //type cast as int for calulation reasons
            val finalResult = convertToDeciplace(resultNum)

            //return the category and bmi results
            val cat = setCategory(resultNum)
            bmiCalculated.text = finalResult
            bmiCategory.text = cat


//            }


        }
    }
    //text listener object for the metric system
    private val textListenerMetric = object:TextWatcher{
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            //checks if the fields is empty
            val heightMain = heightFeetIn.checkValid()
            val weightMain = weightIn.checkValid()
            // calculates and formats the answer
            val resultNum = bmiCalcMetric(heightMain, weightMain)
            val finalResult = convertToDeciplace(resultNum)

            //sets category and results
            val cat = setCategory(resultNum)
            bmiCalculated.text = finalResult
            bmiCategory.text = cat

        }
    }
    //used by list view to initiate the standard unit fields
    private fun setStandardUnits(heightMain:EditText?, heightSecond:EditText?, weightMain:EditText?){

        //sets the visibility of second editText
        if(heightSecond!!.visibility == View.GONE){
            heightSecond!!.visibility = View.VISIBLE
        }
        //set hints to fit the unit system (standard)
        heightMain!!.setHint(R.string.heightStandardHint)
        heightSecond!!.setHint(R.string.heightStandardHintExtra)
        weightMain!!.setHint(R.string.weightStandardHint)

        // set text to reset the calculation
        heightMain!!.setText("")
        heightSecond!!.setText("")
        weightMain!!.setText("")


        //add listener for the fields (standard units)
        heightMain!!.addTextChangedListener(textListenerStandard)
        heightSecond!!.addTextChangedListener(textListenerStandard)
        weightMain!!.addTextChangedListener(textListenerStandard)


    }
    //used by list view to initiate metric unit fields
    internal fun setMetricUnits(heightMain:EditText?, heightSecond:EditText?, weightMain:EditText?){

        //sets the visibility of second EditText to false to hide
        if(heightSecond!!.visibility == View.VISIBLE) {
            heightSecond!!.visibility = View.GONE //sets the visibility of the 2nd field to not be usable
        }

        //sets hints
        heightMain!!.setHint(R.string.heightMetricHint)
        weightMain!!.setHint(R.string.weightMetricHint)

        //clears the editText
        heightMain!!.setText("")
        weightMain!!.setText("")

        //set the textListener object for the metric system
        heightMain!!.addTextChangedListener(textListenerMetric)
        weightMain!!.addTextChangedListener(textListenerMetric)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        //set variable for textviews and edit text

        val heightInMain = heightFeetIn
        val heightInSecond = heightInchesIn
        val weightInMain = weightIn

        //applies adapter to listview for unit selection
        val adapt = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, unitList)

        unitListView.adapter = adapt

        //call methods for which unit system is selected
        unitListView.setOnItemClickListener { _, _, position, _ ->
            when(unitList[position]){
                //calls method and listener based on unit system
                "Standard" -> setStandardUnits(heightInMain, heightInSecond, weightInMain)
                "Metric" -> setMetricUnits(heightInMain, heightInSecond, weightInMain)
            }
        }

        //sets default units as US standard for calculation
        unitListView.setSelection(0)
        setStandardUnits(heightInMain, heightInSecond, weightInMain)

    }


}

//

//return the calculated BMI based on the standard unit system (need to make more accurate with metric and vice versa)
private fun bmiCalcStandard(heightFeet: Int, heightInches: Int, weightPounds: Int):Double{
    //calculate using [weight (lb) / height (in) / height (in)] x 703

    val totalInches = (heightFeet * 12) + heightInches

    val bmiResulted: Double?

    bmiResulted= (weightPounds/Math.pow(totalInches.toDouble(), 2.00)) * 703

    return bmiResulted

}

//check calculate based on metric system units
private fun bmiCalcMetric(heightCentimeters: Double, weightKilograms: Double): Double{
    val bmiResulted :Double?

    bmiResulted = (weightKilograms/ Math.pow((heightCentimeters*0.01), 2.00))

    return bmiResulted

}

//returns the bmi Category string for setting on textView
private fun setCategory(bmiResults: Double): String{
    return if (bmiResults < 18.5){
        "Underweight"
    }
    else if(bmiResults >= 18.5 && bmiResults < 25.0){
        "Normal Weight"
    }
    else if (bmiResults >= 25.0 && bmiResults < 30.0){
        "Overweight"
    }
    else{
        "Obese"
    }
}

