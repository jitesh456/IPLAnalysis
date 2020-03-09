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
    public enum Match{
        RUN,WICKET
    }
    public Map<SortedField,Comparator<CricketDTO>> sortMap;

    List<CricketDTO> cricketInfo;
    public CricketAnalyzer() {
        sortMap=new HashMap<>();
        sortMap.put(SortedField.AVERAGE,Comparator.comparing(sortField->sortField.average));
        sortMap.put(SortedField.STRIKE_RATE,Comparator.comparing(sortField->sortField.strikeRate));
        sortMap.put(SortedField.SORT_ON_6_AND_4,Comparator.comparing(coutOf6And4->coutOf6And4.coutOf6s+coutOf6And4.coutOf4s));
        Comparator<CricketDTO> comparing6And4 = Comparator.comparing(coutOf6And4Object->coutOf6And4Object.coutOf6s+coutOf6And4Object.coutOf4s);
        Comparator<CricketDTO> comparingStrikeRate = Comparator.comparing(sortField -> sortField.strikeRate);
        Comparator<CricketDTO> comparingAverage = Comparator.comparing(sortField -> sortField.average);
        sortMap.put(SortedField.STRIKE_RATE_WITH_6_AND_4,comparing6And4.thenComparing(strikeRateObjet->strikeRateObjet.strikeRate));
        sortMap.put(SortedField.AVERAGE_AND_STRIKE_RATE,comparingStrikeRate.thenComparing(average1->average1.average));
        sortMap.put(SortedField.RUN_WITH_AVERAGE,comparingAverage.thenComparing(totalRun->totalRun.totalRun));
        cricketInfo =new ArrayList<>();
    }

    public void loadIplCsvData(String csvFilePath, Match match) throws IOException {
        try(Reader reader= Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            if(match.equals(Match.RUN)) {
                ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                Iterator<CricketMostRunCSV> csvFileRunIterator = csvBuilder.getCSVFileIterator(reader, CricketMostRunCSV.class);
                Iterable<CricketMostRunCSV> iterable = () -> csvFileRunIterator;
                StreamSupport.stream(iterable.spliterator(), false)
                        .forEach(csvInfo -> cricketInfo.add(new CricketDTO(csvInfo)));
            }else if(match.equals(Match.WICKET))
            {
                ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                Iterator<CricketMostWicketCSV> csvFileRunIterator = csvBuilder.getCSVFileIterator(reader,CricketMostWicketCSV.class );
                Iterable<CricketMostWicketCSV> iterable = () -> csvFileRunIterator;
                StreamSupport.stream(iterable.spliterator(), false).forEach(bowlerInfo->cricketInfo.add(new CricketDTO(bowlerInfo)));
            }
            else
                throw new IPLException("not a valid match type",IPLException.ExceptionType.NOT_A_VALID_TYPE);
        }catch (IPLException e) {

            throw new IPLException("Csv file problem",IPLException.ExceptionType.CSV_FILE_PROBLEM);
        }


    }
    public String sortDataOn(SortedField sortedField)
    {
        Comparator<CricketDTO> cricketMostRunCSVComparator = sortMap.get(sortedField);
        this.sort(cricketMostRunCSVComparator);
        String sortedData = new Gson().toJson(cricketInfo);
        return sortedData;
    }
    private void sort(Comparator<CricketDTO> censusCSVComparator) {

        for (int i = 0; i < cricketInfo.size() - 1; i++) {
            for (int j = 0; j < cricketInfo.size() - i - 1; j++) {
                CricketDTO census1 = cricketInfo.get(j);
                CricketDTO census2 = cricketInfo.get(j + 1);
                if (censusCSVComparator.compare(census1, census2) > 0) {
                    cricketInfo.set(j, census2);
                    cricketInfo.set(j + 1, census1);
                }
            }
        }
    }
}
