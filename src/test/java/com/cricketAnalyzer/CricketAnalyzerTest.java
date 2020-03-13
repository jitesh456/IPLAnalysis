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
        cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE, CricketAnalyzer.Match.RUN);
        String orderByAvarageData =cricketAnalyzer.sortDataOn(SortedField.AVERAGE);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderByAvarageData, CricketMostRunCSV[].class);
        Assert.assertEquals("MS Dhoni",cricketMostRunCSV[cricketMostRunCSV.length-1].player);
    }

    @Test
    public void givenIPLRunData_Whenproper_ShouldReturnSortedStrikeRate() throws IOException {
        CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE, CricketAnalyzer.Match.RUN);
        String orderByStrikeRateData = cricketAnalyzer.sortDataOn( SortedField.STRIKE_RATE);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderByStrikeRateData, CricketMostRunCSV[].class);
        Assert.assertEquals("Ishant Sharma", cricketMostRunCSV[cricketMostRunCSV.length - 1].player);
    }

    @Test
    public void givenIPLRunData_WhenProper_ShouldReturnSortedPlayerRateOnBasicOf4and6Count() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE, CricketAnalyzer.Match.RUN);
        String orderBy4And6Count = cricketAnalyzer.sortDataOn(SortedField.SORT_ON_6_AND_4);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderBy4And6Count, CricketMostRunCSV[].class);
        Assert.assertEquals("Andre Russell",cricketMostRunCSV[cricketMostRunCSV.length-1].player);

    }
    @Test
    public void givenIPLRunData_WhenProper_ShouldReturnSortedStrikeRateRateOnBasicOf4and6Count() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE, CricketAnalyzer.Match.RUN);
        String orderBy4And6Count = cricketAnalyzer.sortDataOn(SortedField.STRIKE_RATE_WITH_6_AND_4);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderBy4And6Count, CricketMostRunCSV[].class);
        Assert.assertEquals("Andre Russell",cricketMostRunCSV[cricketMostRunCSV.length-1].player);
    }

    @Test
    public void givenIPLRunData_WhenProper_ShouldReturnSortedDataOnBasicOfAverageAndStrikeRate() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE, CricketAnalyzer.Match.RUN);
        String orderBy4And6Count = cricketAnalyzer.sortDataOn(SortedField.AVERAGE_AND_STRIKE_RATE);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderBy4And6Count, CricketMostRunCSV[].class);
        Assert.assertEquals("Ishant Sharma",cricketMostRunCSV[cricketMostRunCSV.length-1].player);
    }
    @Test
    public void givenIPLRunData_WhenProper_ShouldReturnSortedDataOnBasicOfTotalRunAndStrikeRate() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_RUN_CSV_FILE, CricketAnalyzer.Match.RUN);
        String orderByTotalRun = cricketAnalyzer.sortDataOn(SortedField.RUN_WITH_AVERAGE);
        CricketMostRunCSV[] cricketMostRunCSV = new Gson().fromJson(orderByTotalRun, CricketMostRunCSV[].class);
        Assert.assertEquals("MS Dhoni",cricketMostRunCSV[cricketMostRunCSV.length-1].player);
    }
    @Test
    public void givenIPLWicketData_WhenProper_ShouldReturnSortedDataOnBasecOfBowlingAverage() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_WICKET_CSV_FILE,CricketAnalyzer.Match.WICKET);
        String orderByAverage = cricketAnalyzer.sortDataOn(SortedField.AVERAGE);
        CricketDTO[] cricketMostRunCSV = new Gson().fromJson(orderByAverage,CricketDTO[].class);
        Assert.assertEquals("Krishnappa Gowtham",cricketMostRunCSV[cricketMostRunCSV.length-1].player);

    }

    @Test
    public void givenIplWicketData_WhenProper_ShouldReturnSortedDataOnBasicOfBowlingStrikeRate() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_WICKET_CSV_FILE,CricketAnalyzer.Match.WICKET);
        String sortedDataOnBasicOfBowlingStrikeRate = cricketAnalyzer.sortDataOn(SortedField.STRIKE_RATE);
        CricketDTO[] cricketDTOS = new Gson().fromJson(sortedDataOnBasicOfBowlingStrikeRate, CricketDTO[].class);
        Assert.assertEquals("Krishnappa Gowtham",cricketDTOS[cricketDTOS.length-1].player);

    }

    @Test
    public void givenIplWicketData_WhenProper_ShouldReturnSortedDataOnBasicOfBowlingEconomy() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_WICKET_CSV_FILE,CricketAnalyzer.Match.WICKET);
        String sortedDataOnBasicOfBowlingStrikeRate = cricketAnalyzer.sortDataOn(SortedField.ECONOMY);
        CricketDTO[] cricketDTOS = new Gson().fromJson(sortedDataOnBasicOfBowlingStrikeRate, CricketDTO[].class);
        Assert.assertEquals("Ben Cutting",cricketDTOS[cricketDTOS.length-1].player);
    }
    @Test
    public void givenIplWicketData_WhenProper_ShouldReturnSortedDataOnBasicOfWicketAndStrikeRate() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_WICKET_CSV_FILE,CricketAnalyzer.Match.WICKET);
        String sortedDataOnBasicOfBowlingStrikeRate = cricketAnalyzer.sortDataOn(SortedField.STRIKE_RATE_WITH_5Wicket_AND_4Wicket);
        CricketDTO[] cricketDTOS = new Gson().fromJson(sortedDataOnBasicOfBowlingStrikeRate, CricketDTO[].class);
        Assert.assertEquals("Krishnappa Gowtham",cricketDTOS[cricketDTOS.length-1].player);
    }
    @Test
    public void givenIplWicketData_WhenProper_ShouldReturnSortedDataOnBasicOfBowlingAverageAndStrikeRate() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_WICKET_CSV_FILE,CricketAnalyzer.Match.WICKET);
        String sortedDataOnBasicOfBowlingAverageAndStrikeRate = cricketAnalyzer.sortDataOn(SortedField.AVERAGE_AND_STRIKE_RATE);
        CricketDTO[] cricketDTOS = new Gson().fromJson(sortedDataOnBasicOfBowlingAverageAndStrikeRate, CricketDTO[].class);
        Assert.assertEquals("Krishnappa Gowtham",cricketDTOS[cricketDTOS.length-1].player);
    }
    @Test
    public void givenIplWicketData_WhenProper_ShouldReturnSortedDataOnBasicOfMaxWicketAndAverage() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        cricketAnalyzer.loadIplCsvData(IPL_MOST_WICKET_CSV_FILE,CricketAnalyzer.Match.WICKET);
        String sortedDataOnBasicOfBowlingStrikeRate = cricketAnalyzer.sortDataOn(SortedField.MAXIMUM_WICKET_AND_AVERAGE);
        CricketDTO[] cricketDTOS = new Gson().fromJson(sortedDataOnBasicOfBowlingStrikeRate, CricketDTO[].class);
        Assert.assertEquals("Imran Tahir",cricketDTOS[cricketDTOS.length-1].player);
    }
    @Test
    public void givenIplWicketData_WhenProper_ShouldReturnSortedDataOnBasicOfBattingAndBowlingAverage() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        String sortOnAverageOfBowlingAndBatting = cricketAnalyzer.analyseIPLData(SortedField.ALL_ROUNDER_AVERAGE, IPL_MOST_RUN_CSV_FILE, IPL_MOST_WICKET_CSV_FILE);
        IplAllRounderDTO[] iplAllRounderDTOS = new Gson().fromJson(sortOnAverageOfBowlingAndBatting, IplAllRounderDTO[].class);
        Assert.assertEquals("Marcus Stoinis",iplAllRounderDTOS[iplAllRounderDTOS.length-1].player);

    }
    @Test
    public void givenIplWicketData_WhenProper_ShouldReturnSortedDataOnBasicOfTotalRunAndWicket() throws IOException {
        CricketAnalyzer cricketAnalyzer=new CricketAnalyzer();
        String sortOnAverageOfBowlingAndBatting = cricketAnalyzer.analyseIPLData(SortedField.ALL_ROUNDER_WICKET_AND_RUN, IPL_MOST_RUN_CSV_FILE, IPL_MOST_WICKET_CSV_FILE);
        IplAllRounderDTO[] iplAllRounderDTOS = new Gson().fromJson(sortOnAverageOfBowlingAndBatting, IplAllRounderDTO[].class);
        Assert.assertEquals("Krishnappa Gowtham",iplAllRounderDTOS[iplAllRounderDTOS.length-1].player);
    }

}
