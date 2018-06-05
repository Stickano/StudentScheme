package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {

    private String firstName;
    private String lastName;
    private String id;
    private Date dateOfBirth;

    /**
     * Constructor for models.Person (students)
     * @param firstName     Persons First name
     * @param lastName      Persons Last name
     * @param dateOfBirth   Persons date of birth
     */
    public Person(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.id = SetId();
    }

    /**
     * Empty Constructor for invalid searches
     */
    public Person() {
        this.firstName = "";
        this.lastName = "";
        this.dateOfBirth = new Date();
        this.id = "";
    }

    /**
     * This will define the UID for a models.Person.
     * (dob + fname + lname)
     * @return              The generated ID
     */
    private String SetId(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        //Date date = dateFormat.parse(String.valueOf(this.dateOfBirth.toString()));
        String dateConverter = dateFormat.format(this.dateOfBirth);
        return dateConverter + this.firstName.toLowerCase() + this.lastName.toLowerCase();
    }

    /**
     * Returns the UID for the models.Person
     * @return              The unique ID for each models.Person
     */
    public String GetId(){
        return this.id;
    }

    /**
     * Getter for First Name
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for First Name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for Last Name
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for Last Name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the Date of Birth as a Date object
     * @return      (Date) Date of Birth
     */
    public Date getDateOfBirth() { return dateOfBirth; }
}
