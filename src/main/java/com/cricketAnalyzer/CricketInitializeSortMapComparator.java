package com.cricketAnalyzer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CricketInitializeSortMapComparator
{
    public Map<SortedField,Comparator<CricketDTO>> sortMap;
    public  Map<SortedField, Comparator<IplAllRounderDTO>> sortMapAllRounder;
    public CricketInitializeSortMapComparator() {
        sortMap=new HashMap<>();
        sortMapAllRounder=new HashMap<>();
        sortMapAllRounder.put(SortedField.ALL_ROUNDER_AVERAGE, Comparator.comparing(averageField->averageField.average));
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
    }
}
