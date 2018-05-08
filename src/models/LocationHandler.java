package models;

import resources.CsvReader;
import resources.Parser;

import java.util.ArrayList;
import java.util.List;

public class LocationHandler {

    private List<Location> locations;

    public LocationHandler() {
        this.locations = new ArrayList<>();
        readCsv();
    }

    /**
     * Will read the location csv file and populate the list of locations
     */
    private void readCsv () {
        CsvReader locations = new CsvReader("csv/locations.csv");
        for (String col[] : locations.getResults()){
            int phone = -1;
            if (Parser.isInt(col[1]))
                phone = Integer.parseInt(col[1]);
            this.locations.add(new Location(col[0], phone, col[2]));
        }
    }

    public List<Location> getLocations() {
        return this.locations;
    }

}
