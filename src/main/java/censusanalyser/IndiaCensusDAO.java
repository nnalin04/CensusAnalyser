package censusanalyser;

public class IndiaCensusDAO {
    public String state;
    public int areaInSqKm;
    public int densityPerSqKm;
    public int population;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public IndiaCensusCSV getIndiaCensusCSV(){
        return new IndiaCensusCSV(state, population, densityPerSqKm, areaInSqKm);
    }
}
