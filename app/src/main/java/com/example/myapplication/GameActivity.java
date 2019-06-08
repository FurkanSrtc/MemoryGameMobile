package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;

import java.sql.Time;
import java.util.*;

public class GameActivity extends AppCompatActivity {

    private static final int AcilacakKartSayisi = 5;
    private static final int NUM_ROWS = 3;
    private static final int NUM_COLS = 3;
    private static int randomRows[] = new int[NUM_ROWS];
    private static int randomCols[] = new int[NUM_COLS];



    Handler handler;
    Runnable runnable;

    Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];

    List<Button> btnList=new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        populateButtons();

        ButtonTimer();
    }


    int sayac = 0;
    int kartKapatanSayac = 0;

    int randomRow;
    int randomCol;
    Random rnd = new Random();

    int[] acilacakRowlar = new int[AcilacakKartSayisi];
    int[] acilacakColumnlar = new int[AcilacakKartSayisi];
String a="";
    private void kontrol(int row, int col) {
        if (buttons[row][col].getBackground() == (getDrawable(R.drawable.buttonacik))) {
            row = rnd.nextInt(NUM_ROWS);
            col = rnd.nextInt(NUM_COLS);
            kontrol(row, col);
        } else {
            buttons[row][col].setBackground(getDrawable(R.drawable.buttonacik));
            acilacakRowlar[sayac] = row;
            acilacakColumnlar[sayac] = col;
        }
    }

    public void ButtonTimer() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {


                if (sayac < AcilacakKartSayisi) {

                    randomRow = rnd.nextInt(NUM_ROWS);
                    randomCol = rnd.nextInt(NUM_COLS);
                    if (sayac==0) handler.postDelayed(this, 1500);
                   else handler.postDelayed(this, 400);

                    kontrol(randomRow, randomCol);

                    a+=acilacakRowlar[sayac] +"-"+acilacakColumnlar[sayac]+" | ";
                    sayac++;

                } else {
                    /*
TextView textView=(TextView)findViewById(R.id.textView6);
textView.setText(a);*/
                    //for (int i=0; i<AcilacakKartSayisi)
                    if (kartKapatanSayac < AcilacakKartSayisi) {
                        buttons[acilacakRowlar[kartKapatanSayac]][acilacakColumnlar[kartKapatanSayac]].setBackground(getDrawable(R.drawable.buttonkapalikart));
                        handler.postDelayed(this, 400);
                        kartKapatanSayac++;
                    } else { //for (int i=0; i<AcilacakKartSayisi)
                        handler.removeCallbacks(runnable);
                    }

                }
            }
        };
        handler.post(runnable);
    }


    private void populateButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.layoutTable);

        for (int row = 0; row < NUM_ROWS; row++) {
            final TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));


            table.addView(tableRow);

            for (int col = 0; col < NUM_COLS; col++) {

                final int FINAL_COL = col;
                final int FINAL_ROW = row;

                final Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                //     button.setText("" + col + "," + row);

                button.setId(((col + 1) * 10) + (row + 1));
                // Make text not clip on small buttons
                button.setPadding(10, 10, 10, 10);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_COL, FINAL_ROW);
                    }
                });

                button.setBackground(getDrawable(R.drawable.buttonkapalikart));

//button.setVisibility(View.INVISIBLE);

                tableRow.addView(button);

                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int col, int row) {
        Toast.makeText(this, "Button clicked: " + col + "," + row,
                Toast.LENGTH_SHORT).show();
        Button button = buttons[row][col];

        // Lock Button Sizes:
        lockButtonSizes();

        // Does not scale image.
//    	button.setBackgroundResource(R.drawable.action_lock_pink);

        // Scale image to button: Only works in JellyBean!
        // Image from Crystal Clear icon set, under LGPL
        // http://commons.wikimedia.org/wiki/Crystal_Clear
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.buttonkapalikart);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));

        // Change text on button:
        //  button.setText("" + col);  KOLON SAYISINI ÜSTÜNE YAZ

    }

    private void lockButtonSizes() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }


}
