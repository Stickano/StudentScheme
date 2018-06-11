package resources;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private String csvFile;
    private List<String[]> results;

    /**
     * Constructor
     * @param csvFile   The CSV file to read (./csv/*.csv)
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
     * @return      (List of Arrays) Separated results
     */
    public List<String[]> getResults(){
        return this.results;
    }

    /**
     * Write a new line to this.csvFile
     * @param values    The values to write on the new line
     *                  (Values will be double-quoted and comma separated)
     */
    public void writeLine(String[] values) {

        String line = "";

        int br = 0;
        for (String value : values) {
            line += "\"" + value + "\"";
            if (br+1 < values.length)
                line += ",";
            br++;
        }

        try {
            FileWriter writer = new FileWriter(this.csvFile,true);
            writer.write(line);
            writer.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // TODO: Just delete and create the object again
    public void updateLine() {

    }
}
