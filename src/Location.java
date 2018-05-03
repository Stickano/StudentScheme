public class Location {

    private int phone;
    private String id;
    private String location;

    public Location(String id, int phone, String location) {
        this.id = id;
        this.phone = phone;
        this.location = location;
    }

    public int GetPhone(){
        return this.phone;
    }

    public String GetLocation(){
        return this.location;
    }

    public String GetId(){
        return this.id;
    }
}
