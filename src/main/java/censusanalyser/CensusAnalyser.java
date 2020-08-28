package censusanalyser;


import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {

    Map<String, CensusDAO> censusStateMap = null;

    public CensusAnalyser() {   }

    public int loadCensusData(CensusComparator.Country country,  String... csvFilePath) throws CensusAnalyserException {
        censusStateMap = new CensusLoader().loadCensusData(country, csvFilePath);
        return censusStateMap.size();
    }

    public final String getStateCensusData(Comparator<CensusDAO>... comparators) {
        List<CensusDAO> censusDAOS;
        if (comparators.length == 1) {
            censusDAOS = censusStateMap.values().stream()
                    .sorted(comparators[0]).collect(Collectors.toList());
        } else {
            censusDAOS = censusStateMap.values().stream()
                    .sorted(comparators[0].thenComparing(comparators[1])).collect(Collectors.toList());
        }
        return new Gson().toJson(censusDAOS);
    }
}
