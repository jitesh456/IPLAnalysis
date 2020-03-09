package com.cricketAnalyzer;

import com.opencsv.bean.CsvBindByName;

public class CricketMostRunCSV {
    //POS,PLAYER,Mat,Inns,NO,Runs,HS,Avg,BF,SR,100,50,4s,6s
    @CsvBindByName(column = "Avg",required = true)
    public double average;
    @CsvBindByName(column = "PLAYER")
    public String player;
    @CsvBindByName(column = "SR")
    public double strikeRate;
    @CsvBindByName(column = "4s")
    public int coutOf4s;
    @CsvBindByName(column = "6s")
    public int coutOf6s;
    @CsvBindByName(column = "Runs")
    public int totalRun;

    @Override
    public String toString() {
        return "CricketMostRunCSV{" +
                "average=" + average +
                ", player='" + player + '\'' +
                ", strikeRate=" + strikeRate +
                '}';
    }
}


