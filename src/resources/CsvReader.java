package resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CsvReader {

    private String csvFile;
    private String error;
    private String splitBy;
    private String[][] results;

    /**
     * Constructor
     * @param csvFile
     */
    public CsvReader(String csvFile) {
        this.csvFile = csvFile;
        this.splitBy = ",";
        readCsv();
    }

    /**
     * Will read the CSV file into an array
     */
    private void readCsv(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.csvFile));
            String line = "";
            int br = 0;
            while ((line = reader.readLine()) != null){
                this.results[br] = line.split(this.splitBy);
                br++;
            }
        }catch (Exception e){
            this.error = e.getMessage();
        }
    }

    /**
     * In case I wanna change the split by symbol
     * Default ','
     * @param symbol
     */
    public void changeSplitBy(String symbol){
        this.splitBy = symbol;
    }

    /**
     * In case of error, call this during test ie.
     * @return      (String) error message
     */
    public String getError(){
        return this.error;
    }

    /**
     * Returns all the results from the given CSV file
     * @return      (Array) Separated results
     */
    public String[][] getResults(){
        return this.results;
    }
}
