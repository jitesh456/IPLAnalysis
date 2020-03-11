package com.cricketAnalyzer;

public class CricketDTO {

    public double average;
    public String player;
    public double strikeRate;
    public int coutOf4s;
    public int coutOf6s;
    public int totalRun;
    public  double economy;
    public int wicket4;
    public int wicket5;


    public CricketDTO(CricketMostRunCSV mostRunCSVObj) {
        this.average = mostRunCSVObj.average;
        this.player = mostRunCSVObj.player;
        this.strikeRate = mostRunCSVObj.strikeRate;
        this.coutOf4s = mostRunCSVObj.coutOf4s;
        this.coutOf6s = mostRunCSVObj.coutOf6s;
        this.totalRun = mostRunCSVObj.totalRun;
    }

    public CricketDTO(CricketMostWicketCSV mostWicketCSVObj) {
        this.player = mostWicketCSVObj.player;
        this.average = mostWicketCSVObj.average;
        this.strikeRate=mostWicketCSVObj.strikeRate;
        this.economy=mostWicketCSVObj.economy;
        this.wicket4=mostWicketCSVObj.wicket4;
        this.wicket5=mostWicketCSVObj.wicket5;
    }
}
