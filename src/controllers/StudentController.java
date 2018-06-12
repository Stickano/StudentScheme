package controllers;

import models.Person;
import models.TimetableEvent;
import resources.CsvReader;
import resources.Parser;

import java.util.*;

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

    public Map<String, Person> getStudentsDictionary() {
        return this.students;
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

    /**
     * This will confirm that a provided Event ID is valid (enroll to course ie.)
     * @param eventId   The ID of the course (event)
     * @return          True/False
     */
    public boolean checkEventId(String eventId) {
        CsvReader readEvents = new CsvReader("csv/events.csv");
        for (String[] event : readEvents.getResults()) {
            if (event[1].equals(eventId))
                return true;
        }
        return false;
    }

    /**
     * Enroll a student to a course (event)
     * @param studentId The ID of the student
     * @param eventId   The ID of the course (event)
     */
    public void enrollStudent(String studentId, String eventId) {
        CsvReader enrollments = new CsvReader("csv/enrollments.csv");
        String[] values = {studentId, eventId};
        enrollments.writeLine(values);
    }

    /**
     * Gets the enrolled courses of a student
     * This is a little messy and could prolly be refactored.
     * It borrows a lil' csvReading logic from the TimetableController.
     * TODO: should prolly do something about that.
     * @param studentID The ID of the student, to find courses for
     * @return          The enrolled courses (events) for the student
     */
    public List<TimetableEvent> getStudentEnrollments(String studentID) {

        CsvReader reader;
        Map<String, String[]> timetableEvents = new HashMap<>();
        List<TimetableEvent> studentEvents = new LinkedList<>();

        // Add all the courses (events) to a dictionary to quickly be able and search events from IDs
        reader = new CsvReader("csv/events.csv");
        for (String[] event : reader.getResults()) {
            timetableEvents.put(event[1], event);
        }

        // Match student ID to enrollments,
        // then search that course ID in the above dictionary,
        // and lastly create a TimetableEvent object, with the values from the dictionary,
        // and add the object to the list that will be returned
        reader = new CsvReader("csv/enrollments.csv");
        for (String[] event : reader.getResults()) {
            if (event[0].equals(studentID) && timetableEvents.containsKey(event[1])) {

                String[] col = timetableEvents.get(event[1]);
                Date startDate = Parser.parseStringToDate(col[4]);
                Date endDate = Parser.parseStringToDate(col[5]);

                // Parse ints (start/end time & day of week)
                int startTime = -1;
                int endTime = -1;
                int dow = -1;
                if (Parser.isInt(col[6]))
                    startTime = Integer.parseInt(col[6]);
                if (Parser.isInt(col[7]))
                    endTime = Integer.parseInt(col[7]);
                if (Parser.isInt(col[2]))
                    dow = Integer.parseInt(col[2]);

                studentEvents.add(new TimetableEvent(col[1], dow, col[3], startDate, endDate, startTime, endTime));
            }
        }

        return studentEvents;
    }

    public void removeStudent(String studentId) {

    }
}
