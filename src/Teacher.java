import java.util.Date;

public class Teacher extends Person{

    private double salary;
    private Date startDate;

    /**
     * Constructor for teacher. Extends Person.
     * Adds Salary and Start Date
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     * @param salary
     * @param startDate
     */
    public Teacher(String firstName, String lastName, Date dateOfBirth, double salary, Date startDate) {
        super(firstName, lastName, dateOfBirth);
        this.salary = salary;
        this.startDate = startDate;
    }

    /**
     * Getter for teacher salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Setter for teacher salary
     * @param salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }
}
