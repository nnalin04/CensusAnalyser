package censusanalyser;

import java.util.Comparator;

public class CensusComparator {

    public enum Country {
        INDIA, US
    }

    static Comparator<CensusDAO> getState = Comparator.comparing(census -> census.state);
    static Comparator<CensusDAO> getStateCode = Comparator.comparing(census -> census.stateCode);
    static Comparator<CensusDAO> getPopulation = Comparator.comparing(census -> census.population);
    static Comparator<CensusDAO> getPopulationDensity = Comparator.comparing(census -> census.populationDensity);
    static Comparator<CensusDAO> getTotalArea = Comparator.comparing(census -> census.totalArea);
}
