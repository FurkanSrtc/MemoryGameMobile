package com.example.myapplication

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.print.PrintAttributes
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TableRow
import kotlinx.android.synthetic.main.activity_game_menu.*

class GameMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_menu)

        createTable(3, 3)
    }



 private fun createTable(rows: Int, cols: Int){


     for (i in 0 until rows)
     {
      val tableRow=TableRow(this)
         tableRow.layoutParams  = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
             ViewGroup.LayoutParams.WRAP_CONTENT)

         for (j in 0 until cols) {

             val button = ImageButton(this)
             button.apply {
                 layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                     TableRow.LayoutParams.WRAP_CONTENT)

                 setImageResource(R.drawable.buttonkapalikart)
setPadding(20,20,0,0)
                 setBackgroundColor(16777215)
             }

             tableRow.addView(button)
         }
         tableButtons.addView(tableRow)
     }


     }


    }



