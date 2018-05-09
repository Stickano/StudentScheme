package controllers;

import models.Person;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentController {

    private Map<String, Person> students;

    public StudentController() {
        this.students = new HashMap<>();
    }

    public Collection<Person> getStudents() {
        return this.students.values();
    }
}
