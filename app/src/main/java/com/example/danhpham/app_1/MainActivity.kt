package com.example.danhpham.app_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.content.Intent
import android.view.View

const val EXTRA_MESSAGE = "com.example.danhpham.app_1"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



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

            R.id.gpa->{
                // val i = Intent(<action>, <uri> ) implicit intent

                true;
            }

            R.id.tips->{

                val intent = Intent(this, Tips::class.java)
                startActivity(intent)
                true;
            }

            else->{
                super.onOptionsItemSelected(item)
            }
        }
    }
}
