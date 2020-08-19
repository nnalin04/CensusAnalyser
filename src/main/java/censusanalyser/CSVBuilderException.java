package censusanalyser;

public class CSVBuilderException extends Exception {

    enum ExceptionType {
        UNABLE_TO_PARSE, CENSUS_FILE_PROBLEM, NOT_A_CSV_TYPE_OR_HEADERS_INVALID
    }

    ExceptionType type;

    public CSVBuilderException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
