package censusanalyser;


import com.google.gson.Gson;
import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    Map<String, IndiaCensusDAO> censusStateMap = null;

    public CensusAnalyser() {
        censusStateMap = new HashMap<>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            Iterable<IndiaCensusCSV> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(censusCSV -> censusStateMap.put(censusCSV.state, new IndiaCensusDAO(censusCSV)) );
            return censusStateMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public int loadIndiaStateCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCSV> stateCodeCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            Iterable<IndiaStateCodeCSV> csvIterable = () -> stateCodeCSVIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusStateMap.get(csvState.stateName) != null)
                    .forEach(csvState -> censusStateMap.get(csvState.stateName).stateCode = csvState.stateCode);
            return censusStateMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    private <E> int getCount(Iterator<E> iterator){
        Iterable<E> csvIterable = () -> iterator;
        return (int) StreamSupport.stream(csvIterable.spliterator(), false).
                                                                                   count();
    }

    public String getStateWiseSortedsetsData() {
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        List<IndiaCensusDAO> censusDAOS = censusStateMap.values().stream()
                .collect(Collectors.toList());
        this.sort(censusDAOS, censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusDAOS);
        return sortedStateCensusJson;
    }
    public String getStateCodeWiseSortedsetsData() {
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.stateCode);
        List<IndiaCensusDAO> censusDAOS = censusStateMap.values().stream()
                .collect(Collectors.toList());
        this.sort(censusDAOS, censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusDAOS);
        return sortedStateCensusJson;
    }

    public String getStatePopulationWiseSortedsetsData() {
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        List<IndiaCensusDAO> censusDAOS = censusStateMap.values().stream()
                .collect(Collectors.toList());
        this.sort(censusDAOS, censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusDAOS);
        return sortedStateCensusJson;
    }

    public String getStatePopulationDensityWiseSortedsetsData() {
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        List<IndiaCensusDAO> censusDAOS = censusStateMap.values().stream()
                                                                 .collect(Collectors.toList());
        this.sort(censusDAOS, censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusDAOS);
        return sortedStateCensusJson;
    }

    public String getStateAreaWiseSortedsetsData() {
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        List<IndiaCensusDAO> censusDAOS = censusStateMap.values().stream()
                .collect(Collectors.toList());
        this.sort(censusDAOS, censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusDAOS);
        return sortedStateCensusJson;
    }

    private void sort(List<IndiaCensusDAO> censusDAOS, Comparator<IndiaCensusDAO> censusComparator) {
        for (int i = 0; i < censusDAOS.size()-1; i++) {
            for (int j =0; j< censusDAOS.size() -i -1; j++) {
                IndiaCensusDAO census1 = censusDAOS.get(j);
                IndiaCensusDAO census2 = censusDAOS.get(j+1);
                if (censusComparator.compare(census1, census2) > 0){
                    censusDAOS.set(j, census2);
                    censusDAOS.set(j+1, census1);
                }
            }
        }
    }


}
