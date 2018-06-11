package controllers;

import models.Person;
import resources.CsvReader;
import resources.Parser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentController {

    private Map<String, Person> students;
    private CsvReader csv;

    public StudentController() {
        csv = new CsvReader("csv/students.csv");
        this.students = new HashMap<>();
        readCsv();
    }

    /**
     * Reads all the records from the student CSV file and stores them in a dictionary
     */
    private void readCsv() {
        for (String[] student : this.csv.getResults()) {
            Person person = new Person(student[0], student[1], Parser.parseStringToDate(student[2]));
            this.students.put(person.GetId(), person);
        }
    }

    /**
     * Returns all students in a collection (like list ie)
     * @return  (Collection) of all the students - Person Objects
     */
    public Collection<Person> getStudents() {
        return this.students.values();
    }

    /**
     * Get a individual student
     * @param id    The ID of the student
     * @return      (Person) The student object
     */
    public Person getStudent(String id) {
        Person person = new Person();
        if (this.students.containsKey(id))
            person = this.students.get(id);
        return person;
    }

    /**
     * Creates a new student to the CSV file
     * @param student   The student (Person) object to create
     */
    public void createStudent(Person student) {
        String[] values = {student.getFirstName(), student.getLastName(), Parser.parseDateToString(student.getDateOfBirth())};
        csv.writeLine(values);
    }
}
