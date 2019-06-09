package com.example.myapplication;

public class Level {
    private int level;

    public Level(int level, int row, int column, int yanacaKart) {
        this.level = level;
        this.row = row;
        this.column = column;
        this.yanacaKart = yanacaKart;
    }

    private int row;
    private int column;
    private int yanacaKart;

    public int getLevel() {
        return level;
    }

    public void setLevel(int seviye) {
        this.level = seviye;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getYanacaKart() {
        return yanacaKart;
    }

    public void setYanacaKart(int yanacaKart) {
        this.yanacaKart = yanacaKart;
    }


}
