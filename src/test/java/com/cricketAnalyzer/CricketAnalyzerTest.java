package com.cricketAnalyzer;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CricketAnalyzerTest {
    public static final String IPL_MOST_RUN_CSV_FILE="./src/test/resources/IPL2019FactsheetMostRuns.csv";
    @Test
    public void givenIPLRunData_WhenProper_ShouldReturnSortedAvarageRun() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        String orderByAvarageData = cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE,SortedField.AVERAGE);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderByAvarageData, CricketMostRunCSV[].class);
        Assert.assertEquals("MS Dhoni",cricketMostRunCSV[cricketMostRunCSV.length-1].player);
    }

    @Test
    public void givenIPLRunData_Whenproper_ShouldReturnSortedStrikeRate() throws IOException {
        CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
        String orderByStrikeRateData = cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE, SortedField.STRIKERATE);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderByStrikeRateData, CricketMostRunCSV[].class);
        Assert.assertEquals("Ishant Sharma", cricketMostRunCSV[cricketMostRunCSV.length - 1].player);
    }

    @Test
    public void givenIPLRunData_WhenProper_ShouldReturnSortedStrikeRateOnBasicOf4and6Count() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        String orderBy4And6Count = cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE, SortedField.SORT_ON_6_AND_4);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderBy4And6Count, CricketMostRunCSV[].class);
        Assert.assertEquals("Andre Russell",cricketMostRunCSV[cricketMostRunCSV.length-1].player);

    }
}
