package com.example.myapplication;

public class Level {

    private int combo;

    public int getCombo() {
        return combo;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getTekrar() {
        return tekrar;
    }

    public void setTekrar(int tekrar) {
        this.tekrar = tekrar;
    }

    public int getAcilacakKartSayisi() {
        return AcilacakKartSayisi;
    }

    public void setAcilacakKartSayisi(int acilacakKartSayisi) {
        AcilacakKartSayisi = acilacakKartSayisi;
    }

    public int getNUM_ROWS() {
        return NUM_ROWS;
    }

    public void setNUM_ROWS(int NUM_ROWS) {
        this.NUM_ROWS = NUM_ROWS;
    }

    public int getNUM_COLS() {
        return NUM_COLS;
    }

    public void setNUM_COLS(int NUM_COLS) {
        this.NUM_COLS = NUM_COLS;
    }

    private int score;
    private int level;
    private int life;
    private int tekrar;
    private int AcilacakKartSayisi;
    private int NUM_ROWS;

    public Level(int combo, int score, int level, int life, int tekrar, int acilacakKartSayisi, int NUM_ROWS, int NUM_COLS) {
        this.combo = combo;
        this.score = score;
        this.level = level;
        this.life = life;
        this.tekrar = tekrar;
        AcilacakKartSayisi = acilacakKartSayisi;
        this.NUM_ROWS = NUM_ROWS;
        this.NUM_COLS = NUM_COLS;
    }

    private int NUM_COLS;

    public Level() {

    }






}
