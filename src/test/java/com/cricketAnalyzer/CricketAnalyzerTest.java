package com.cricketAnalyzer;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CricketAnalyzerTest {
    public static final String IPL_MOST_RUN_CSV_FILE="./src/test/resources/IPL2019FactsheetMostRuns.csv";
    public static  final String IPL_MOST_WICKET_CSV_FILE="./src/test/resources/IPL2019FactsheetMostWkts.csv";
    @Test
    public void givenIPLRunData_WhenProper_ShouldReturnSortedAvarageRun() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE);
        String orderByAvarageData =cricketAnalyzer.sortDataOn(SortedField.AVERAGE);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderByAvarageData, CricketMostRunCSV[].class);
        Assert.assertEquals("MS Dhoni",cricketMostRunCSV[cricketMostRunCSV.length-1].player);
    }

    @Test
    public void givenIPLRunData_Whenproper_ShouldReturnSortedStrikeRate() throws IOException {
        CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE);
        String orderByStrikeRateData = cricketAnalyzer.sortDataOn( SortedField.STRIKE_RATE);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderByStrikeRateData, CricketMostRunCSV[].class);
        Assert.assertEquals("Ishant Sharma", cricketMostRunCSV[cricketMostRunCSV.length - 1].player);
    }

    @Test
    public void givenIPLRunData_WhenProper_ShouldReturnSortedPlayerRateOnBasicOf4and6Count() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE);
        String orderBy4And6Count = cricketAnalyzer.sortDataOn(SortedField.SORT_ON_6_AND_4);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderBy4And6Count, CricketMostRunCSV[].class);
        Assert.assertEquals("Andre Russell",cricketMostRunCSV[cricketMostRunCSV.length-1].player);

    }
    @Test
    public void givenIPLRunData_WhenProper_ShouldReturnSortedStrikeRateRateOnBasicOf4and6Count() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE);
        String orderBy4And6Count = cricketAnalyzer.sortDataOn(SortedField.STRIKE_RATE_WITH_6_AND_4);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderBy4And6Count, CricketMostRunCSV[].class);
        Assert.assertEquals("Andre Russell",cricketMostRunCSV[cricketMostRunCSV.length-1].player);
    }

    @Test
    public void givenIPLRunData_WhenProper_ShouldReturnSortedDataOnBasicOfAverageAndStrikeRate() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE);
        String orderBy4And6Count = cricketAnalyzer.sortDataOn(SortedField.AVERAGE_AND_STRIKE_RATE);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderBy4And6Count, CricketMostRunCSV[].class);
        Assert.assertEquals("Ishant Sharma",cricketMostRunCSV[cricketMostRunCSV.length-1].player);
    }
    @Test
    public void givenIPLRunData_WhenProper_ShouldReturnSortedDataOnBasicOfTotalRunAndStrikeRate() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE);
        String orderBy4And6Count = cricketAnalyzer.sortDataOn(SortedField.RUN_WITH_AVERAGE);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderBy4And6Count, CricketMostRunCSV[].class);
        Assert.assertEquals("MS Dhoni",cricketMostRunCSV[cricketMostRunCSV.length-1].player);
    }


}
