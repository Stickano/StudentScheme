package models;

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
     * This will define the UID for a models.Person.
     * (dob + fname + lname)
     * @return              The generated ID
     */
    private String SetId(){
        String dateConverter = this.dateOfBirth.toString();
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
}
