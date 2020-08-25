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
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) { }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
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
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_TYPE);
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
            censusAnalyser.loadIndiaCensusData(SAMPLE);
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
            censusAnalyser.loadIndiaCensusData(SAMPLE);
        }catch(CensusAnalyserException  e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE_OR_HEADERS_INVALID,e.type);
        }
    }


    @Test
    public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCode(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37,numOfRecords);
        } catch (CensusAnalyserException e) { }
    }

    @Test
    public void givenIndiaStateCode_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
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
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_TYPE);
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
            censusAnalyser.loadIndiaCensusData(SAMPLE);
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
            censusAnalyser.loadIndiaCensusData(SAMPLE);
        }catch(CensusAnalyserException  e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE_OR_HEADERS_INVALID,e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCode(INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedsetsData();
            IndiaCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        }catch (CensusAnalyserException e ) { }
    }

    @Test
    public void givenIndianStateCode_WhenSortedOnStateCode_ShouldReturnSortedResult() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCode(INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCodeWiseSortedsetsData();
            IndiaStateCodeCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, IndiaStateCodeCSV[].class);
            Assert.assertEquals("AD", censusCSV[0].stateCode);
        }catch (CensusAnalyserException e ) { }
    }

    @Test
    public void givenIndianStateCensus_WhenSortedOnStatePopulation_ShouldReturnSortedResult() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCode(INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStatePopulationWiseSortedsetsData();
            IndiaCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Sikkim", censusCSV[0].state);
        }catch (CensusAnalyserException e ) { }
    }

    @Test
    public void givenIndianStateCensus_WhenSortedOnStatePopulationDensity_ShouldReturnSortedResult() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCode(INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStatePopulationDensityWiseSortedsetsData();
            IndiaCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Arunachal Pradesh", censusCSV[0].state);
        }catch (CensusAnalyserException e ) { }
    }

    @Test
    public void givenIndianStateCensus_WhenSortedOnStateArea_ShouldReturnSortedResult() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCode(INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateAreaWiseSortedsetsData();
            IndiaCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Goa", censusCSV[0].state);
        }catch (CensusAnalyserException e ) { }
    }

    @Test
    public void givenUSCensusData_ShouldReturnsCorrectRecords() throws CensusAnalyserException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
                int numOfRecords = censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51,numOfRecords);
    }

    @Test
    public void givenUSCensusData_WhenSortedOnStateCode_ShouldReturnSortedResult() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCodeWiseSortedsetsData();
            USCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals(null, censusCSV[0].stateId);;
        }catch (CensusAnalyserException e ) { }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnStatePopulation_ShouldReturnSortedResult() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStatePopulationWiseSortedsetsData();
            USCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Wyoming", censusCSV[0].state);
        }catch (CensusAnalyserException e ) { }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnStatePopulationDensity_ShouldReturnSortedResult() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStatePopulationDensityWiseSortedsetsData();
            USCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Alaska", censusCSV[0].state);
        }catch (CensusAnalyserException e ) { }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnStateArea_ShouldReturnSortedResult() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateAreaWiseSortedsetsData();
            USCensusCSV[] censusCSV =  new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("District of Columbia", censusCSV[0].state);
        }catch (CensusAnalyserException e ) { }
    }

    @Test
    public void givenIndianAndUSCensusData_WhenSortedOnStatePopulation_ShouldReturnSortedResult() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedUSCensusData = censusAnalyser.getStatePopulationWiseSortedsetsData();
            USCensusCSV[] USCensusCSV =  new Gson().fromJson(sortedUSCensusData, USCensusCSV[].class);
            Assert.assertEquals("Wyoming", USCensusCSV[0].state);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCode(INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedIndiaCensusData = censusAnalyser.getStatePopulationWiseSortedsetsData();
            IndiaCensusCSV[] indiaCensusCSV =  new Gson().fromJson(sortedIndiaCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Sikkim", indiaCensusCSV[0].state);
        }catch (CensusAnalyserException e ) { }
    }
}
