package valueC;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Currencies {
    private boolean success;
    private long timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;
    private Map<String, Double> ratesNames; //TODO

    public boolean isSuccess() {
        return success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public Map<String, Double> getRatesNames() {
        return ratesNames;
    }

    public void setRatesNames(Map<String, Double> names) {
        ratesNames=names;
    }
}
