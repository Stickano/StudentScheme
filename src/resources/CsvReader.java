package resources;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private String csvFile;
    private List<String[]> results;

    /**
     * Constructor
     * @param csvFile
     */
    public CsvReader(String csvFile) {
        this.csvFile = csvFile;
        this.results = new ArrayList<>();
        readCsv();
    }

    /**
     * Will read the CSV file into an array
     */
    private void readCsv(){
        try {
            CSVReader reader = new CSVReader(new FileReader(this.csvFile));
            String[] line;
            while ((line = reader.readNext()) != null){
                this.results.add(line);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns all the results from the given CSV file
     * @return      (Array) Separated results
     */
    public List<String[]> getResults(){
        return this.results;
    }
}
