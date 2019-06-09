package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.nfc.Tag;
import android.os.CountDownTimer;
import android.os.Handler;
import android.print.PrintAttributes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.AdView;

import java.sql.Time;
import java.util.*;

public class GameActivity extends AppCompatActivity {

    private static final int AcilacakKartSayisi = 4;
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 3;
    int sayac = 0;
    int randomRow;
    int randomCol;
    int kartKapatanSayac = 0;
    Random rnd = new Random();
    int[] acilacakRowlar = new int[AcilacakKartSayisi];
    int[] acilacakColumnlar = new int[AcilacakKartSayisi];


    private AdView mAdView;

    Handler handler;
    Runnable runnable;

    Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];

    List<Button> btnList = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
// TODO: Add adView to your view hierarchy.

        MobileAds.initialize(this,"ca-app-pub-5098083474917427~7900549557");


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        populateButtons();

        ButtonTimer();


    }




    private void kontrol(int row, int col) {
        if (buttons[row][col].getTag() == "acik") {
            row = rnd.nextInt(NUM_ROWS);
            col = rnd.nextInt(NUM_COLS);
            kontrol(row, col);
        } else {
            buttons[row][col].setBackground(getDrawable(R.drawable.buttonacik));
            buttons[row][col].setTag("acik");
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

                    kontrol(randomRow, randomCol);
                    if (sayac == 0)
                    {
                        handler.postDelayed(this, 1000);
                    }
                    else {

                            buttons[acilacakRowlar[sayac-1]][acilacakColumnlar[sayac-1]].setBackground(getDrawable(R.drawable.buttonkapalikart));

                        handler.postDelayed(this, 400);
                    }

                    sayac++;

                } else {
                    if (sayac==AcilacakKartSayisi)
                    buttons[acilacakRowlar[sayac-1]][acilacakColumnlar[sayac-1]].setBackground(getDrawable(R.drawable.buttonkapalikart));

                    /*
TextView textView=(TextView)findViewById(R.id.textView6);
textView.setText(a);*/
                    //for (int i=0; i<AcilacakKartSayisi)
                   /*
                    if (kartKapatanSayac < AcilacakKartSayisi) {
                        buttons[acilacakRowlar[kartKapatanSayac]][acilacakColumnlar[kartKapatanSayac]].setBackground(getDrawable(R.drawable.buttonkapalikart));
                        handler.postDelayed(this, 400);
                        kartKapatanSayac++;
                    } else { //for (int i=0; i<AcilacakKartSayisi)
                        handler.removeCallbacks(runnable);
                    } */
                    //for (int i=0; i<AcilacakKartSayisi)
                    Toast.makeText(getApplicationContext(), "SIRA SENDE !",
                            Toast.LENGTH_SHORT).show();
                    handler.removeCallbacks(runnable);


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

         /*  ////////
            TableLayout.LayoutParams tableRowParams=new TableLayout.LayoutParams();
            tableRowParams.setMargins(20,10,20,10);
            ;
            tableRow.setLayoutParams(tableRowParams);
            ////////*/
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
                button.setPadding(0, 0, 0, 0);

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
    int clicksayac=0;
    private void gridButtonClicked(int col, int row) {
        Toast.makeText(this, "Button clicked: " + col + "," + row,
                Toast.LENGTH_SHORT).show();
        Button button = buttons[row][col];

        // Lock Button Sizes:
        //lockButtonSizes();            //////////////////////////////////-----------LOCK BUTTON SIZE

        // Does not scale image.
//    	button.setBackgroundResource(R.drawable.action_lock_pink);

        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
       if (col==acilacakColumnlar[clicksayac] && row==acilacakRowlar[clicksayac] )
       {

           Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.buttonacik);
           Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
           Resources resource = getResources();
           button.setBackground(new BitmapDrawable(resource, scaledBitmap));
           clicksayac++;
           if (clicksayac==AcilacakKartSayisi)
           {
               Toast.makeText(this, "KAZANDIN",
                       Toast.LENGTH_SHORT).show();
           }
       }
        // Scale image to button: Only works in JellyBean!
        // Image from Crystal Clear icon set, under LGPL
        // http://commons.wikimedia.org/wiki/Crystal_Clear
      else
       {
           Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.buttonhatakart);
           Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
           Resources resource = getResources();
           button.setBackground(new BitmapDrawable(resource, scaledBitmap));
       }


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
