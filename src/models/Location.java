package models;

public class Location {

    private int phone;
    private String id;
    private String location;

    /**
     * Constructor
     * @param id        Unique school ID
     * @param phone     Phone number
     * @param location  Address
     */
    public Location(String id, int phone, String location) {
        this.id = id;
        this.phone = phone;
        this.location = location;
    }

    /**
     * Empty constructor to return for invalid ID's
     */
    public Location(){};

    /**
     * Returns the Phone number of a location
     * @return  (int) The phone number
     */
    public int GetPhone(){
        return this.phone;
    }

    /**
     * Returns the Address of the location
     * @return  (String) The location
     */
    public String GetLocation(){
        return this.location;
    }

    /**
     * Returns the Id of a location
     * @return  (String) The unique school ID
     */
    public String GetId(){
        return this.id;
    }
}
