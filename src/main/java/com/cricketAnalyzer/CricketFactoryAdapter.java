package com.cricketAnalyzer;

import java.io.IOException;
import java.util.List;

public class CricketFactoryAdapter {

    public static List<CricketDTO> getCricketData(String csvFilePath, CricketAnalyzer.Match match) throws IOException {
        if(match.equals(CricketAnalyzer.Match.RUN))
        return new CricketRunCsvLoader().loadIplCsvData(csvFilePath);
        else if(match.equals(CricketAnalyzer.Match.WICKET))
            return  new CricketWicketCsvLoader().loadIplCsvData(csvFilePath);
        else
            throw new IPLException("Invalid match type",IPLException.ExceptionType.NO_SUCH_TYPE);

    }
}
