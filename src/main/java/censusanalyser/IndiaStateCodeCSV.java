package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {

    @CsvBindByName(column = "State", required = true)
    public String stateName;

    @CsvBindByName(column = "Population", required = true)
    public String stateCode;

    @Override
    public String toString() {
        return "IndiaStateCodeCSV{" +
                "stateName='" + stateName + '\'' +
                ", stateCode=" + stateCode +
                '}';
    }
}
