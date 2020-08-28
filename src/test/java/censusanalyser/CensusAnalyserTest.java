package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_TYPE = "./src/test/resources/wrongFile.txt";
    private static final String SAMPLE = "./src/test/resources/SampleForHeaderAndDelimiter.csv";
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";

    @Test
    public void givenIndianCensusCSVFile_ShouldReturnsCorrectRecords() throws CensusAnalyserException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(CensusComparator.Country.INDIA,
                    INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusComparator.Country.INDIA, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WhenWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusComparator.Country.INDIA, WRONG_CSV_TYPE);
        }catch (CensusAnalyserException e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE_OR_HEADERS_INVALID, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WhenDelimiterIncorrect_ShouldThrowException() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusComparator.Country.INDIA, SAMPLE);
        }catch(CensusAnalyserException  e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE_OR_HEADERS_INVALID,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WhenHeaderIncorrect_ShouldThrowException() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusComparator.Country.INDIA, SAMPLE);
        }catch(CensusAnalyserException  e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE_OR_HEADERS_INVALID,e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusComparator.Country.INDIA, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WhenWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusComparator.Country.INDIA, WRONG_CSV_TYPE);
        }catch (CensusAnalyserException e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE_OR_HEADERS_INVALID, e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WhenDelimiterIncorrect_ShouldThrowException() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusComparator.Country.INDIA, SAMPLE);
        }catch(CensusAnalyserException  e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE_OR_HEADERS_INVALID,e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WhenHeaderIncorrect_ShouldThrowException() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusComparator.Country.INDIA, SAMPLE);
        }catch(CensusAnalyserException  e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE_OR_HEADERS_INVALID,e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() throws CensusAnalyserException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusComparator.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCensusData(CensusComparator.getState);
            IndiaCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
    }

    @Test
    public void givenIndianStateCode_WhenSortedOnStateCode_ShouldReturnSortedResult() throws CensusAnalyserException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusComparator.Country.INDIA,
                    INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCensusData(CensusComparator.getStateCode);
            IndiaStateCodeCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, IndiaStateCodeCSV[].class);
            Assert.assertEquals("AP", censusCSV[0].stateCode);
    }

    @Test
    public void givenIndianStateCensus_WhenSortedOnStatePopulation_ShouldReturnSortedResult()
            throws CensusAnalyserException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusComparator.Country.INDIA,
                    INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCensusData(CensusComparator.getPopulation);
            IndiaCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Sikkim", censusCSV[0].state);
    }

    @Test
    public void givenIndianStateCensus_WhenSortedOnStatePopulationDensity_ShouldReturnSortedResult()
            throws CensusAnalyserException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusComparator.Country.INDIA,
                    INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCensusData(CensusComparator.getPopulationDensity);
            IndiaCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Arunachal Pradesh", censusCSV[0].state);
    }

    @Test
    public void givenIndianStateCensus_WhenSortedOnStateArea_ShouldReturnSortedResult() throws CensusAnalyserException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusComparator.Country.INDIA,
                    INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCensusData(CensusComparator.getTotalArea);
            IndiaCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Goa", censusCSV[0].state);
    }

    @Test
    public void givenUSCensusData_ShouldReturnsCorrectRecords() throws CensusAnalyserException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
                int numOfRecords = censusAnalyser.loadCensusData(CensusComparator.Country.US, US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51,numOfRecords);
    }

    @Test
    public void givenUSCensusData_WhenSortedOnStateCode_ShouldReturnSortedResult() throws CensusAnalyserException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusComparator.Country.US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCensusData(CensusComparator.getStateCode);
            USCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("AK", censusCSV[0].stateCode);
    }

    @Test
    public void givenUSCensusData_WhenSortedOnStatePopulation_ShouldReturnSortedResult() throws CensusAnalyserException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusComparator.Country.US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCensusData(CensusComparator.getPopulation);
            USCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Wyoming", censusCSV[0].state);
    }

    @Test
    public void givenUSCensusData_WhenSortedOnStatePopulationDensity_ShouldReturnSortedResult()
            throws CensusAnalyserException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusComparator.Country.US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCensusData(CensusComparator.getPopulationDensity);
            USCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Alaska", censusCSV[0].state);
    }

    @Test
    public void givenUSCensusData_WhenSortedOnStateArea_ShouldReturnSortedResult() throws CensusAnalyserException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusComparator.Country.US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCensusData(CensusComparator.getTotalArea);
            USCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("District of Columbia", censusCSV[0].state);
    }

    @Test
    public void givenUSCensusData_WhenSortedOnStatePopulationWithDensity_ShouldReturnSortedResult()
            throws CensusAnalyserException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusComparator.Country.US, US_CENSUS_CSV_FILE_PATH);
            String sortedUSCensusData = censusAnalyser.getStateCensusData
                    (CensusComparator.getPopulation, CensusComparator.getPopulationDensity);
            USCensusCSV[] USCensusCSV =  new Gson().fromJson(sortedUSCensusData, USCensusCSV[].class);
            Assert.assertEquals("Wyoming", USCensusCSV[0].state);
    }

    @Test
    public void givenIndiaCensusData_WhenSortedOnStatePopulationWithDensity_ShouldReturnSortedResult()
            throws CensusAnalyserException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusComparator.Country.INDIA,
                    INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedIndiaCensusData = censusAnalyser.getStateCensusData
                    (CensusComparator.getPopulation, CensusComparator.getPopulationDensity);
            IndiaCensusCSV[] indiaCensusCSV =  new Gson().fromJson(sortedIndiaCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Sikkim", indiaCensusCSV[0].state);
    }
}
