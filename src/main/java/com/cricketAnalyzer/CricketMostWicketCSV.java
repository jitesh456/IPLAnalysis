package com.cricketAnalyzer;

import com.opencsv.bean.CsvBindByName;

public class CricketMostWicketCSV {
    //POS,PLAYER,Mat,Inns,Ov,Runs,Wkts,BBI,Avg,Econ,SR,4w,5w,

    @CsvBindByName(column = "PLAYER")
    public String player;

    @CsvBindByName(column = "Avg")
    public double average;

    @Override
    public String toString() {
        return "CricketMostWicketCSV{" +
                "player='" + player + '\'' +
                ", average='" + average + '\'' +
                '}';
    }
}
