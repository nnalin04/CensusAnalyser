package censusanalyser;

public class CensusAnalyserException extends Exception {

    enum ExceptionType {
        NO_CENSUS_DATA, CENSUS_FILE_PROBLEM, NOT_A_CSV_TYPE_OR_HEADERS_INVALID, INVALID_COUNTRY;
    }

    ExceptionType type;

    public CensusAnalyserException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
