package com.cricketAnalyzer;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyzer {
    public enum Match{
        RUN,WICKET
    }

    List<CricketDTO> cricketInfo;
    Map<String, IplAllRounderDTO>  playerData;
    public CricketAnalyzer() {
        playerData=new HashMap<>();

        cricketInfo =new ArrayList<>();
    }

    public void loadIplCsvData(String csvFilePath, Match match) throws IOException {
        cricketInfo=CricketFactoryAdapter.getCricketData(csvFilePath, match);
    }

    public String analyseIPLData(SortedField type, String... csvFilePath) throws IOException {

        List<CricketDTO> runData = CricketFactoryAdapter.getCricketData(csvFilePath[0],Match.RUN);;
        List<CricketDTO> wicketData = CricketFactoryAdapter.getCricketData(csvFilePath[1],Match.WICKET);;
        for (int i = 0; i < runData.size()-1; i++) {
            for (int j = 0; j < wicketData.size(); j++) {
                if (runData.get(i).player.equals(wicketData.get(j).player)) {
                    if (type.equals(SortedField.ALL_ROUNDER_AVERAGE))
                        playerData.put(wicketData.get(i).player, new IplAllRounderDTO(wicketData.get(i).player,wicketData.get(i).average - runData.get(j).average));
                    if (type.equals(SortedField.ALL_ROUNDER_WICKET_AND_RUN))
                        playerData.put(wicketData.get(i).player, new IplAllRounderDTO(wicketData.get(i).player,wicketData.get(i).totalRun * runData.get(j).totalWicket));
                }
            }
        }

        List<IplAllRounderDTO> list = playerData.values().stream().collect(Collectors.toList());
        list.sort(new CricketInitializeSortMapComparator().sortMapAllRounder.get(type));
        return new Gson().toJson(list);
    }


    public String sortDataOn(SortedField sortedField)
    {
        Comparator<CricketDTO> cricketMostRunCSVComparator = new CricketInitializeSortMapComparator().sortMap.get(sortedField);
        this.sort(cricketMostRunCSVComparator);
        String sortedData = new Gson().toJson(cricketInfo);
        return sortedData;
    }

    private void sort(Comparator<CricketDTO> censusCSVComparator) {

        for (int i = 0; i < cricketInfo.size() - 1; i++) {
            for (int j = 0; j < cricketInfo.size() - i - 1; j++) {
                CricketDTO cricketInfo1 = cricketInfo.get(j);
                CricketDTO cricketInfo2 = cricketInfo.get(j + 1);
                if (censusCSVComparator.compare(cricketInfo1, cricketInfo2) > 0) {
                    cricketInfo.set(j, cricketInfo2);
                    cricketInfo.set(j + 1, cricketInfo1);
                }
            }
        }
    }
}
