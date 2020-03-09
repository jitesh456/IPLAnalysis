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
    Map<SortedField,Comparator<CricketMostRunCSV>> sortMap;

    List<CricketMostRunCSV> cricketBattingInfo;
    public CricketAnalyzer() {
        sortMap=new HashMap<>();
        sortMap.put(SortedField.AVERAGE,Comparator.comparing(sortField->sortField.average));
        sortMap.put(SortedField.STRIKE_RATE,Comparator.comparing(sortField->sortField.strikeRate));
        sortMap.put(SortedField.SORT_ON_6_AND_4,Comparator.comparing(coutOf6And4->coutOf6And4.coutOf6s+coutOf6And4.coutOf4s));
        Comparator<CricketMostRunCSV> comparing6And4 = Comparator.comparing(coutOf6And4Object->coutOf6And4Object.coutOf6s+coutOf6And4Object.coutOf4s);
        Comparator<CricketMostRunCSV> comparingStrikeRate = Comparator.comparing(sortField -> sortField.strikeRate);
        Comparator<CricketMostRunCSV> comparingAverage = Comparator.comparing(sortField -> sortField.average);
        sortMap.put(SortedField.STRIKE_RATE_WITH_6_AND_4,comparing6And4.thenComparing(strikeRateObjet->strikeRateObjet.strikeRate));
        sortMap.put(SortedField.AVERAGE_AND_STRIKE_RATE,comparingStrikeRate.thenComparing(average1->average1.average));
        sortMap.put(SortedField.RUN_WITH_AVERAGE,comparingAverage.thenComparing(totalRun->totalRun.totalRun));
        cricketBattingInfo =new ArrayList<>();
    }

    public void loadIplCsvData(String csvFilePath) throws IOException {
        try(Reader reader= Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CricketMostRunCSV> csvFileRunIterator = csvBuilder.getCSVFileIterator(reader, CricketMostRunCSV.class);
            Iterable<CricketMostRunCSV> iterable=()->csvFileRunIterator;
            StreamSupport.stream(iterable.spliterator(),false).forEach(csvInfo-> cricketBattingInfo.add(csvInfo));

        }catch (IPLException e) {

            throw new IPLException("Csv file problem",IPLException.ExceptionType.CSV_FILE_PROBLEM);
        }
        catch (RuntimeException e)
        {
            throw new IPLException("wrong field",IPLException.ExceptionType.NO_SUCH_FIELD);
        }

    }
    public String sortDataOn(SortedField sortedField)
    {
        Comparator<CricketMostRunCSV> cricketMostRunCSVComparator = sortMap.get(sortedField);
        this.sort(cricketMostRunCSVComparator);
        String sortedData = new Gson().toJson(cricketBattingInfo);
        return sortedData;
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
