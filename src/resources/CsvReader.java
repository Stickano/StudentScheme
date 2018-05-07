package resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private String csvFile;
    private String splitBy;
    private List<String[]> results;

    /**
     * Constructor
     * @param csvFile
     */
    public CsvReader(String csvFile) {
        this.csvFile = csvFile;
        this.splitBy = ",";
        this.results = new ArrayList<>();
        readCsv();
    }

    /**
     * Will read the CSV file into an array
     */
    private void readCsv(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.csvFile));
            String line;
            while ((line = reader.readLine()) != null){
                String[] res = line.split(this.splitBy);
                this.results.add(res);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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
     * Returns all the results from the given CSV file
     * @return      (Array) Separated results
     */
    public List<String[]> getResults(){
        return this.results;
    }
}
