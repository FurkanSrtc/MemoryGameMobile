package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String veritabani_adi = "dbhafizaoyunu";
    private static final String oyun_tablosu = "tbl_oyun";
    private static final int db_version = 1;

    public Database(Context context) {
        super(context, veritabani_adi, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//Tabloları oluşturmak için
        String sql_oyuntablosuolustur = "CREATE TABLE " + oyun_tablosu + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Combo INTEGER, Score INTEGER, Level INTEGER, Life INTEGER, Tekrar INTEGER,AcilacaKartSayisi INTEGER,NumRows INTEGER,NumCols INTEGER)";
        db.execSQL(sql_oyuntablosuolustur);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + oyun_tablosu);
    }


    public void ekleBilgi(Level levelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tekrar", levelClass.getTekrar());
        cv.put("combo", levelClass.getCombo());
        cv.put("score", levelClass.getScore());
        cv.put("level", levelClass.getLevel());
        cv.put("life", levelClass.getLife());

       db.insert(oyun_tablosu, null, cv);

    }

    public Level getirOyunBilgileri() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * From " + oyun_tablosu + " Order by id ASC LIMIT 1";

        Cursor c = db.rawQuery(query, null);
        int oyunTekrar=c.getColumnIndex("Tekrar");
        int oyuncombo=c.getColumnIndex("Combo");
        int oyunscore=c.getColumnIndex("Score");
        int oyunlevel=c.getColumnIndex("Level");
        int oyunLife=c.getColumnIndex("Life");
        int acilacakKartSayisi=c.getColumnIndex("AcilacaKartSayisi");
        int numRows=c.getColumnIndex("NumRows");
        int numCols=c.getColumnIndex("NumCols");


        if (c.getCount() > 0) {
            c.moveToFirst();
            Level _level = new Level();
            _level.setCombo(c.getInt(c.getInt(oyunTekrar)));
            _level.setCombo(c.getInt(c.getInt(oyuncombo)));
            _level.setScore(c.getInt(c.getInt(oyunscore)));
            _level.setLevel(c.getInt(c.getInt(oyunlevel)));
            _level.setLife(c.getInt(c.getInt(oyunLife)));
            _level.setAcilacakKartSayisi(c.getInt(c.getInt(acilacakKartSayisi)));
            _level.setNUM_ROWS(c.getInt(c.getInt(numRows)));
            _level.setNUM_COLS(c.getInt(c.getInt(numCols)));

            c.close();
            db.close();
            return _level;
        }
        else {
            return null;
        }


    }


}
