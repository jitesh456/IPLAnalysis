package com.cricketAnalyzer;

import java.io.IOException;
import java.util.List;

public class CricketWicketCsvLoader extends CricketAdapter{
    @Override
    public List <CricketDTO> loadIplCsvData(String csvFilePath) throws IOException {
        List<CricketDTO> cricketDTOS = super.loadIplCsvData(CricketMostWicketCSV.class, csvFilePath);
        return cricketDTOS;
    }
}
