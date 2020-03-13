package com.cricketAnalyzer;

import java.io.IOException;
import java.util.List;

public class CricketRunCsvLoader extends CricketAdapter {

    @Override
    public List<CricketDTO> loadIplCsvData(String csvFilePath) throws IOException {
        List<CricketDTO> cricketDTOS = super.loadIplCsvData(CricketMostRunCSV.class, csvFilePath);
        return cricketDTOS;
    }
}
