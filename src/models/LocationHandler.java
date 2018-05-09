package models;

import resources.CsvReader;
import resources.Parser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LocationHandler {

    private Map<String, Location> locations;
    private CsvReader csv;
    private final String csvLocation = "csv/locations.csv";

    public LocationHandler() {
        this.locations = new HashMap<>();
        csv = new CsvReader(csvLocation);
        readCsv();
    }

    /**
     * Will read the location csv file and populate the list of locations
     */
    private void readCsv () {
        for (String col[] : csv.getResults()){
            int phone = -1;
            if (Parser.isInt(col[1]))
                phone = Integer.parseInt(col[1]);
            this.locations.put(col[0], new Location(col[0], phone, col[2]));
        }
    }

    /**
     * Returns a list of all the locations
     * @return      (List) of Locations
     */
    public List<Location> getLocations() {
        List<Location> locations = new LinkedList<>();
        for (Location location : this.locations.values()) {
            locations.add(location);
        }

        return locations;
    }

    /**
     * Returns a specific location
     * @param schoolID  The ID of the school
     * @return          (Location) The school object
     */
    public Location getLocation(String schoolID) {
        if (locations.containsKey(schoolID))
            return locations.get(schoolID);
        return new Location();
    }

    /**
     * Create a new location in the CSV file
     * @param location      (Locaton) The location to append
     */
    public void createLocation(Location location) {
        String[] values = {location.GetId(), Integer.toString(location.GetPhone()), location.GetLocation()};
        csv.writeLine(values);
    }

    /**
     * Will confirm if a location id exists
     * @param schoolID      The School ID to match against
     * @return              (Bool) True/False
     */
    public boolean confirmLocationId(String schoolID) {
        if (locations.containsKey(schoolID))
            return true;
        return false;
    }
}
