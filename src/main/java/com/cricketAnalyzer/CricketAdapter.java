package com.cricketAnalyzer;

import com.csvfile.CSVBuilderFactory;
import com.csvfile.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public abstract class CricketAdapter {
    List<CricketDTO> cricketInfo=new ArrayList<>();

    public abstract List<CricketDTO> loadIplCsvData(String csvFilePath) throws IOException;
    public <E>List<CricketDTO>  loadIplCsvData(Class csvClass, String csvFilePath) throws IOException {
        try(Reader reader= Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileRunIterator = csvBuilder.getCSVFileIterator(reader, csvClass);
            Iterable<E> iterable = () -> csvFileRunIterator;
            if(csvClass.getName().equals("com.cricketAnalyzer.CricketMostRunCSV")) {
                StreamSupport.stream(iterable.spliterator(), false).map(CricketMostRunCSV.class::cast)
                        .forEach(csvInfo -> cricketInfo.add(new CricketDTO(csvInfo)));

            }else if(csvClass.getName().equals("com.cricketAnalyzer.CricketMostWicketCSV")) {
                StreamSupport.stream(iterable.spliterator(), false).map(CricketMostWicketCSV.class::cast).
                        forEach(bowlerInfo->cricketInfo.add(new CricketDTO(bowlerInfo)));
            }
        }catch (IPLException e) {

            throw new IPLException("Csv file problem",IPLException.ExceptionType.CSV_FILE_PROBLEM);
        }
        return cricketInfo;
    }
}
