package models;

public class StudentEnrollment {

    private Person student;
    private TimetableEvent course;

    public StudentEnrollment(Person student, TimetableEvent course) {
        this.student = student;
        this.course = course;
    }
}
