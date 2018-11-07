package com.example.danhpham.app_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import android.util.Log


class MainActivity : AppCompatActivity() {
    val tag  =  "Lifecycle of main";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(tag , " onCreate()");


    }
    // ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>debug to test the life cycle activite

    override fun onResume()
    {
        super.onResume();
        Log.d(tag , " onResume() ");
    }

    override fun onDestroy()
    {
        super.onDestroy();
        Log.d(tag , "onDestroy()");
    }
    override fun onCreateOptionsMenu(menu: Menu?):Boolean {
        menuInflater.inflate(R.menu.actions, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when(item?.itemId) // ITEMS NOT NULL CHECK BY USING ?
        {
            R.id.tax->{
                val intent = Intent(this, Tax::class.java)
                startActivity(intent) // call asyncronus
                true;

            }

            R.id.BMI->{
                val intent = Intent(this, BMI::class.java)
                startActivity(intent) //call asynchronous
                true;
            }

            R.id.tips->{

                val intent = Intent(this, Tips::class.java)
                startActivity(intent)
                true;
            }

            R.id.unit_converter->{
                val intent = Intent(this, UnitConverter::class.java)
                startActivity(intent)
                true;
            }

            else->{
                super.onOptionsItemSelected(item)
            }
        }
    }
}
