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
        String avarageWiseSortedData = cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(avarageWiseSortedData, CricketMostRunCSV[].class);
        Assert.assertEquals("MS Dhoni",cricketMostRunCSV[cricketMostRunCSV.length-1].player);
    }
    @Test



}
