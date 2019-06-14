package com.example.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.print.PrintAttributes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private static int AcilacakKartSayisi = 1;
    private static int NUM_ROWS = 2;
    private static int NUM_COLS = 2;
    int sayac = 0; //dizilere veri atamak için
    int randomRow;
    int randomCol;
    int kartKapatanSayac = 0;

    Random rnd = new Random();
    int[] acilacakRowlar = new int[AcilacakKartSayisi];
    int[] acilacakColumnlar = new int[AcilacakKartSayisi];
    int clicksayac = 0;
    private AdView mAdView;
    Handler handler;
    Runnable runnable;
    Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];
    List<Button> btnList = new ArrayList<Button>();

int level=0;
int score=-10;
int life=5;
int combo=0;
int combohata=0; //Yanlış seçim yapıldığında artıyor

    Database db;
    Level levelClass;
    Level oyunBilgileri;

    TextView textView;
    TextView txtCombo;
    TextView txtSkor;
    TextView txtLife;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button btn=(Button) findViewById(R.id.buttonStop);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), PauseMenu.class);
                startActivity(intent);

            }
        });

        try {
            OyunBilgileri();
        }
        catch (Exception e) {};


        textView=(TextView)findViewById(R.id.textView6);
        txtCombo= (TextView)findViewById(R.id.txtCombo);
        txtSkor= (TextView)findViewById(R.id.txtScore);
        txtLife=(TextView)findViewById(R.id.txtLife);


        //region REKLAMLAR
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
// TODO: Add adView to your view hierarchy.
        MobileAds.initialize(this, "ca-app-pub-5098083474917427~7900549557");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Log.d("Tag", "onCreate: Furkan Test");
        //endregion


YeniSeviye();
    /*    populateButtons();
        ButtonTimer();*/
    }

    int tekrar = 0; //Seviye Tekrar

    private void SeviyeDusur() {

        life--;

        sayac = 0;
        clicksayac = 0;
        kartKapatanSayac = 0;
        combohata=0;


        textView.setText("Level "+level );
        txtCombo.setText("COMBO: "+combo);
        txtSkor.setText("SCORE: "+score);
        txtLife.setText("LIFE: "+life);
        TableLayout table = (TableLayout) findViewById(R.id.layoutTable);
        table.removeAllViews();



        if (tekrar<=1)
            level=level-2;
        else   level=level-3;

        if (level<=1) {
            level=1;
            AcilacakKartSayisi=1;
            NUM_ROWS=3;
            NUM_COLS=3;
        }
        else {

            score=score-50;
            AcilacakKartSayisi--;

            if (AcilacakKartSayisi == (NUM_ROWS-1 * NUM_COLS-1)) {
                NUM_ROWS--;
                NUM_COLS--;
            }

        }
        TextView textView=(TextView)findViewById(R.id.textView6);
        textView.setText("Level "+level );


        tekrar=-1;
        acilacakRowlar = new int[AcilacakKartSayisi];
        acilacakColumnlar = new int[AcilacakKartSayisi];
        btnList = new ArrayList<Button>();
        buttons = new Button[NUM_ROWS][NUM_COLS];
        level--;




        VerileriAktar();

        Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
        startActivity(intent);

        populateButtons();
        ButtonTimer();

        }


        private  void  VerileriAktar()
        {
            levelClass=new Level(tekrar,combo,score,level,life,AcilacakKartSayisi,NUM_ROWS,NUM_COLS);
            db =new Database(getApplicationContext());
            db.ekleBilgi(levelClass);

         /*    if (id>0)
                 Toast.makeText(getApplicationContext(),"Başarılı",Toast.LENGTH_LONG).show();
             else
                 Toast.makeText(getApplicationContext(),"Başarısız",Toast.LENGTH_LONG).show();*/

        }


        private void OyunBilgileri()
        {
            db=new Database(getApplicationContext());
            oyunBilgileri= db.getirOyunBilgileri();
            tekrar=oyunBilgileri.getTekrar();
            combo=oyunBilgileri.getCombo();
            score=oyunBilgileri.getScore();
            level=oyunBilgileri.getLevel();
            life=oyunBilgileri.getLife();
            AcilacakKartSayisi=oyunBilgileri.getAcilacakKartSayisi();
            NUM_ROWS=oyunBilgileri.getNumRows();
            NUM_COLS=oyunBilgileri.getNumCols();
        }

    public void YeniSeviye() { //Yeni seviyeye geçtiğinde

        VerileriAktar();
       // OyunBilgileri();


        tekrar++;
        sayac = 0;
        clicksayac = 0;
        kartKapatanSayac = 0;
        combohata=0;



        if (combo==0) score+=10;
        else score=score+(10*combo);

        textView.setText("Level "+level );
        txtCombo.setText("COMBO: "+combo);
        txtSkor.setText("SCORE: "+score);
        txtLife.setText("LIFE: "+life);



        TableLayout table = (TableLayout) findViewById(R.id.layoutTable);
        table.removeAllViews();

        if (tekrar <= 2)  tekrar++;  //Aynı kartla 2 bölüm oynasın

        else {
            tekrar = 0;
            AcilacakKartSayisi++;
            if (AcilacakKartSayisi > (NUM_ROWS * NUM_COLS)) {
                NUM_ROWS++;
                NUM_COLS++;

            }
        }
        acilacakRowlar = new int[AcilacakKartSayisi];
        acilacakColumnlar = new int[AcilacakKartSayisi];
        btnList = new ArrayList<Button>();
        buttons = new Button[NUM_ROWS][NUM_COLS];
        level++;
        TextView textView=(TextView)findViewById(R.id.textView6);
        textView.setText("Level "+level );

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

    public void ButtonTimer() { //RANDOM YANAN BUTON SIRALAMASI
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                if (sayac < AcilacakKartSayisi) {

                    randomRow = rnd.nextInt(NUM_ROWS);
                    randomCol = rnd.nextInt(NUM_COLS);

                    kontrol(randomRow, randomCol);
                    if (sayac == 0) {
                        handler.postDelayed(this, 1000);
                    } else {
                        buttons[acilacakRowlar[sayac - 1]][acilacakColumnlar[sayac - 1]].setBackground(getDrawable(R.drawable.buttonkapalikart));
                        handler.postDelayed(this, 400);
                    }
                    sayac++;

                } else {
                    if (sayac == AcilacakKartSayisi)
                        buttons[acilacakRowlar[sayac - 1]][acilacakColumnlar[sayac - 1]].setBackground(getDrawable(R.drawable.buttonkapalikart));

                  /*  Toast.makeText(getApplicationContext(), "SIRA SENDE !",
                            Toast.LENGTH_SHORT).show();*/
                    handler.removeCallbacks(runnable);
                KartEnabled(true);

                }

            }
        };
        handler.post(runnable);
    }

private  void KartEnabled(boolean b)
{
    for (int row = 0; row < NUM_ROWS; row++) {

        for (int col = 0; col < NUM_COLS; col++) {
            buttons[row][col].setEnabled(b);
        }
    }

}

    private void populateButtons() {   //TABLO SATIR SÜTUN ve BUTON AYARI
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

                button.setId(((col + 1) * 10) + (row + 1));
                // Make text not clip on small buttons
                button.setPadding(0, 0, 0, 0);
                button.setEnabled(false);
                button.setTag("kapali");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_COL, FINAL_ROW);
                    }
                });

                button.setBackground(getDrawable(R.drawable.buttonkapalikart));
                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int col, int row) {  //BUTONA TIKLANDIĞINDA
        Button button = buttons[row][col];

        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        if (col == acilacakColumnlar[clicksayac] && row == acilacakRowlar[clicksayac])  //DOĞRU KARTI SEÇERSE
        {
            button.setBackground(getDrawable(R.drawable.buttonacik));
            button.setEnabled(false);

            clicksayac++;
            if (clicksayac == AcilacakKartSayisi)  //TÜM KARTLARI DOĞRU SEÇERSE
            {  final Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
               /* Toast.makeText(this, "KAZANDIN",
                        Toast.LENGTH_SHORT).show();*/
                vibrator.vibrate(200);
                vibrator.vibrate(500);

                if (combohata==0) combo++;


                Intent intent = new Intent(getApplicationContext(), PauseMenu.class);
                intent.putExtra("lvl",level);
                startActivity(intent);
                overridePendingTransition(0, 0);

                YeniSeviye();
            }
        }

        else // YANLIŞ KARTI SEÇERSE
        {

            YanlisKart(button);
        }

    }




    private void YanlisKart(final Button buton)
    {
        //region TIMER (KAPALI - Kartı Gri Yapıp Söndürüyor)
        /*
         button.setBackground(getDrawable(R.drawable.buttonhatakart));
        final Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                  buton.setBackground(getDrawable(R.drawable.buttonkapalikart));
                    handler.removeCallbacks(runnable);
vibrator.vibrate(50);

            }
        };
        handler.postDelayed(runnable,200);
        */
//endregion

        final Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(50);
        combohata++;
        combo=0;
        if (combohata>=3)
        {

            SeviyeDusur();

        }

        txtCombo.setText("COMBO :"+combo);
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
