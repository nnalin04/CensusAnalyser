package censusanalyser;

public class IndiaCensusDAO {
    public String state;
    public int areaInSqKm;
    public int densityPerSqKm;
    public int population;
    public int stateCode;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public IndiaCensusDAO(USCensusCSV censusCSV) {
    }

    public IndiaCensusCSV getIndiaCensusCSV(){
        return new IndiaCensusCSV(state, population, densityPerSqKm, areaInSqKm);
    }
}
