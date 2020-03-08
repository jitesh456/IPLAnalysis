package com.cricketAnalyzer;

import com.csvfile.CSVBuilderFactory;
import com.csvfile.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;



public class CricketAnalyzer {

    List<CricketMostRunCSV> cricketBattingInfo;
    public CricketAnalyzer() {

        cricketBattingInfo =new ArrayList<>();
    }


    public String loadIplCsvData(String csvFilePath) throws IOException {
        try(Reader reader= Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CricketMostRunCSV> csvFileRunIterator = csvBuilder.getCSVFileIterator(reader, CricketMostRunCSV.class);
            Iterable<CricketMostRunCSV> iterable=()->csvFileRunIterator;
            StreamSupport.stream(iterable.spliterator(),false).forEach(csvInfo-> cricketBattingInfo.add(csvInfo));
            Comparator<CricketMostRunCSV> csvComparator=Comparator.comparing(s->s.average);
            this.sort(csvComparator);
            String sortedData = new Gson().toJson(cricketBattingInfo);
            return sortedData;

        }

    }
    private void sort(Comparator<CricketMostRunCSV> censusCSVComparator) {

        for (int i = 0; i < cricketBattingInfo.size() - 1; i++) {
            for (int j = 0; j < cricketBattingInfo.size() - i - 1; j++) {
                CricketMostRunCSV census1 = cricketBattingInfo.get(j);
                CricketMostRunCSV census2 = cricketBattingInfo.get(j + 1);
                if (censusCSVComparator.compare(census1, census2) > 0) {
                    cricketBattingInfo.set(j, census2);
                    cricketBattingInfo.set(j + 1, census1);
                }
            }
        }
    }
}
