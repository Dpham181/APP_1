package com.example.danhpham.app_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.content.Intent

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
                val intent = Intent(this, test::class.java)
                startActivity(intent)
                true;

            }

            R.id.gpa->{

                true;
            }

            R.id.tips->{

                Toast.makeText(this,"You selected ${item.title}, ", Toast.LENGTH_SHORT).show()
                true;
            }

            else->{
                super.onOptionsItemSelected(item)
            }
        }
    }
}