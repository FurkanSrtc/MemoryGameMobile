package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_game_menu.*

class GameMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_menu)

       // createTable(3, 3)
    }


/*
    fun getOnClickDoSomething(button: Button): View.OnClickListener {
        return View.OnClickListener {
            val criteria = button.text.toString()
            if ("Exams" == criteria) {
                Toast.makeText(applicationContext, "Exams Selected", 2).show()
            } else if ("Quizzes" == criteria) {
                Toast.makeText(applicationContext, "Quizzes Selected", 2).show()
            }
        }
    }
*/

/*
*  private fun createTable(rows: Int, cols: Int){


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
                 id = ((i+1)*10)+(j+1)

             }
                button.setOnClickListener(getOnClickDoSomething(button =
                ))
             tableRow.addView(button)

         }
         LayoutTable.addView(tableRow)
     }


     }
* */


    }



