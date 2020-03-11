package com.cricketAnalyzer;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.*;

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
        sortMap.put(SortedField.AVERAGE_AND_STRIKE_RATE,comparingStrikeRate.thenComparing(averageField->averageField.average));
        sortMap.put(SortedField.RUN_WITH_AVERAGE,comparingAverage.thenComparing(totalRunField->totalRunField.totalRun));
        sortMap.put(SortedField.ECONOMY,Comparator.comparing(economyField->economyField.economy));
        sortMap.put(SortedField.STRIKE_RATE_WITH_5Wicket_AND_4Wicket,comparingStrikeRate.thenComparing(wicket4And5Field->wicket4And5Field.wicket4+wicket4And5Field.wicket5));
        Comparator<CricketDTO> comparingWicket = Comparator.comparing(sortField -> sortField.totalWicket);
        sortMap.put(SortedField.MAXIMUM_WICKET_AND_AVERAGE,comparingWicket.thenComparing(maxWicketField->maxWicketField.totalWicket));
        cricketInfo =new ArrayList<>();
    }

    public void loadIplCsvData(String csvFilePath, Match match) throws IOException {

        cricketInfo= CricketFactoryAdapter.getCricketData(csvFilePath, match);
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
