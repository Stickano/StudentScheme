package com.company;

import controllers.StudentController;
import models.Location;
import controllers.LocationController;
import controllers.TimetableController;
import models.Person;
import models.TimetableEvent;
import resources.Parser;
import resources.Usage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        /**
         * This is all very static and a LOT of refactoring would be needed
         * if the parameters should ever change - Relevant for all of the major categories.
         */


        // Print the usage options (--help)
        if (args.length==0 || args[0].equals("-h") || args[0].equals("--help")) {
            System.out.println(Usage.Common());
            System.exit(0);
        }

        // Dictionary of arguments (arg=value)
        Map<String, String> arguments = Parser.splitParameters(args);

        // If too many super arguments has been passed, exit
        int superArguments = Parser.categoryControl(arguments);
        if (superArguments > 1){
            System.out.println("Too many super-categories has been passed");
            System.out.println("Pass only one of the following super-categories: schools, students, events & teachers");
            System.exit(1);
        }

        // If no super arguments has been passed, exit
        if (superArguments < 1) {
            System.out.println("Pass at least one of the super-categories: schools, students, events, & teacher");
            System.out.println("--help for usage information");
            System.exit(1);
        }


        /**
         * Schools and its options
         */
        if (arguments.containsKey("-schools")){

            LocationController locations = new LocationController();

            // Display all schools
            if (arguments.size() == 1){
                for (Location location : locations.getLocations()) {
                    System.out.println();
                    System.out.println(location.GetId());
                    System.out.println(location.GetLocation());
                    System.out.println(location.GetPhone());
                }
            }

            // Show specific school
            if (arguments.size() == 2 && arguments.containsKey("-id")) {
                Location location = locations.getLocation(arguments.get("-id"));
                System.out.println();
                System.out.println(location.GetId());
                System.out.println(location.GetLocation());
                System.out.println(location.GetPhone());
            }

            // Delete a school TODO
            if (arguments.size() == 2 && arguments.containsKey("-delete")) {

            }

            // Update a school TODO
            if (arguments.size() > 3 && arguments.size() < 6 && arguments.containsKey("-update")) {

            }

            // Create a new school
            if (arguments.size() == 5 && arguments.containsKey("-create")){
                if (!arguments.containsKey("-location") || !arguments.containsKey("-phone") || !arguments.containsKey("-id")) {
                    System.out.println("Missing one of the following arguments: Location, Phone or Id");
                    System.out.println("--help for usage information");
                    System.exit(1);
                }

                if (!Parser.isInt(arguments.get("-phone"))) {
                    System.out.println("The Phone argument should be a numeric value (integer)");
                    System.exit(1);
                }

                int phone = Integer.parseInt(arguments.get("-phone"));
                Location location = new Location(arguments.get("-id"), phone, arguments.get("-location"));
                locations.createLocation(location);
            }
        }


        /**
         * Events and its options
         */
        if (arguments.containsKey("-events") && arguments.size() >= 2) {

            // Check that we have a school ID
            if (!arguments.containsKey("-school")) {
                System.out.println("Missing School ID (-school=\"School ID\")");
                System.out.println("--help for usage information");
                System.exit(1);
            }

            // Match the school ID to a location (or throw error if none found) and initialize a timetable object
            LocationController locations = new LocationController();
            if (!locations.confirmLocationId(arguments.get("-school"))) {
                System.out.println("Incorrect School ID");
                System.out.println("--help for usage information");
            }

            Location location = locations.getLocation(arguments.get("-school"));
            TimetableController events = new TimetableController(location);
            SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy");

            // Show all events for a school
            if (arguments.size() == 2) {
                String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                for (int i=0; i<7; i++) {
                    System.out.println();
                    System.out.println(weekDays[i]);
                    for (TimetableEvent event : events.GetDayEvents(i)) {
                        System.out.println("    " + event.getId());
                        System.out.println("    " + df.format(event.getStartDate()) + " - " + df.format(event.getEndDate()));
                        System.out.println("    " + event.getStartTime() + " - " + event.getEndTime());
                        System.out.println("        " + event.getEventInformation());
                        System.out.println();
                    }
                }
            }

            // Show specific event
            if (arguments.containsKey("-id") && arguments.size() == 3) {
                System.out.println();
                TimetableEvent event = events.GetEvent(arguments.get("-id"));
                System.out.println(event.getId());
                System.out.println(df.format(event.getStartDate()) + " - " + df.format(event.getEndDate()));
                System.out.println(event.getStartTime() + " - " + event.getEndTime());
                System.out.println("    " + event.getEventInformation());
                System.out.println();
            }

            // Create an event
            if (arguments.containsKey("-create") && arguments.size() == 8) {
                // Control all arguments are available
                if (!arguments.containsKey("-id")
                        && !arguments.containsKey("-day")
                        && !arguments.containsKey("-info")
                        && !arguments.containsKey("-date")
                        && !arguments.containsKey("-time")){
                    System.out.println("Missing one of the following arguments: Id, Day, Info, Date or Time");
                    System.out.println("--help for usage information");
                    System.exit(1);
                }

                // Split date (start/end). Confirm it has two values
                String[] dates = arguments.get("-date").split("-");
                if (dates.length != 2){
                    System.out.println("Date string is wrong");
                    System.out.println("Date format is YearMonthDay - All numeric");
                    System.out.println("The expected input is: \"startDate-endDate\"");
                    System.exit(1);
                }

                // Control the two values are integers
                for (String date : dates) {
                    date = date.replace("\"","");
                    if (!Parser.isInt(date)) {
                        System.out.println("Date format is wrong");
                        System.out.println("Date format is YearMonthDay - All numeric");
                        System.out.println("The expected input is: \"startDate-endDate\"");
                        System.exit(1);
                    }
                }

                // Try and parse the dates values into Date objects
                Date startDate = new Date();
                Date endDate = new Date();
                try {
                    SimpleDateFormat dformat = new SimpleDateFormat("yyyyMMdd");
                    startDate = dformat.parse(dates[0]);
                    endDate = dformat.parse(dates[1]);
                } catch (ParseException e){
                    System.out.println("Could not parse value into date: " + e.getMessage());
                    System.exit(1);
                }

                // Split time. Confirm it has two values
                String[] times = arguments.get("-time").split("-");
                if (times.length != 2) {
                    System.out.println("Time format is wrong");
                    System.out.println("Time format is HourMinute - All numeric");
                    System.out.println("The expected input is: startTime-endTime");
                    System.exit(1);
                }

                // Control the times are integer
                for (String time : times) {
                    time = time.replace("\"", "");
                    if (!Parser.isInt(time)) {
                        System.out.println("Time format is wrong");
                        System.out.println("Time format is HourMinute - All numeric");
                        System.out.println("The expected input is: startTime-endTime");
                        System.exit(1);
                    }
                }

                int startTime = Integer.parseInt(times[0]);
                int endTime = Integer.parseInt(times[1]);

                // Check that day of week is an integer
                String dow = arguments.get("-day");
                dow = dow.replace("\"", "");
                if (!Parser.isInt(dow)) {
                    System.out.println("Day value should be numeric (day of week)");
                    System.out.println("0 = Monday,  6 = Sunday");
                }

                int day = Integer.parseInt(dow);

                // Try and add the new event - If the ID is not unique, an exception will be thrown
                try {
                    events.AddEvent(arguments.get("-id"), startDate, endDate, arguments.get("-info"), day, startTime, endTime);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

            // Delete an event TODO
            if (arguments.containsKey("-delete") && arguments.size() == 3) {

            }

            // Update an event TODO
            if (arguments.containsKey("-update") && arguments.size() >= 4) {

            }
        }


        /**
         * Students and its options
         */
        if (arguments.containsKey("-students")) {
            StudentController students = new StudentController();

            // Print all students
            if (arguments.size() == 1) {
                for (Person student : students.getStudents()) {
                    System.out.println();
                    System.out.println(student.getFirstName() + " " + student.getLastName());
                    System.out.println("ID: " + student.GetId());
                }
            }

            // Show specific student TODO: Marks and Enrollments
            if (arguments.containsKey("-id") && arguments.size() == 2){
                Person student = students.getStudent(arguments.get("-id"));
                DateFormat df = new SimpleDateFormat("MMM dd yyyy");

                System.out.println();
                System.out.println(student.getFirstName() + " " + student.getLastName());
                System.out.println("Born: " + df.format(student.getDateOfBirth()));

                System.out.println();
                System.out.println("Enrollments: ");
                for (TimetableEvent event : students.getStudentEnrollments(arguments.get("-id"))) {
                    System.out.println(event.getId() + ": " + event.getEventInformation());
                }
            }

            // Create new student TODO test
            if (arguments.containsKey("-create") && arguments.size() == 4) {
                if (!arguments.containsKey("-name") || !arguments.containsKey("-dob")) {
                    System.out.println("Missing one of the following arguments: Name or Date of Birth");
                    System.out.println("--help for usage information");
                    System.exit(1);
                }

                // Check dob is an integer value
                String dobString = arguments.get("-dob");
                dobString = dobString.replace("\"", "");
                if (!Parser.isInt(dobString)) {
                    System.out.println("Day of Birth value should be numeric (yyyyMMdd)");
                    System.exit(1);
                }

                // Split the name (fname/lname)
                String name[] = arguments.get("-name").split(" ", 2);
                if (name.length == 1) {
                    System.out.println("Really? No last name. Poor kid. Default character assigned: minus (-)");
                    name[1] = "-";
                }

                // Convert date
                Date dob = Parser.parseStringToDate(arguments.get("-dob"));

                // Create a Person (student) object and pass to controller
                Person student = new Person(name[0], name[1], dob);
                students.createStudent(student);
            }

            // Update a student TODO
            if (arguments.containsKey("-update") && arguments.size() == 3 ) {

            }

            // Delete a student TODO
            if (arguments.containsKey("-delete") && arguments.size() == 2) {
                if (!students.getStudentsDictionary().containsKey("-delete")){
                    System.out.println("Invalid Student ID. Student not found.");
                }

                students.removeStudent(arguments.get("-delete"));
            }

            // Enroll (or remove) a student to courses TODO
            if (arguments.containsKey("-enroll") && arguments.size() == 4 || arguments.size() == 5) {
                if (!arguments.containsKey("-student") || !arguments.containsKey("-class")) {
                    System.out.println("Missing one of the following arguments: Student or Class ID");
                    System.out.println("--help for usage information");
                    System.exit(1);
                }

                // Check student ID is valid
                if (!students.getStudentsDictionary().containsKey(arguments.get("-student"))){
                    System.out.println("Invalid Student ID. Student not found.");
                    System.exit(1);
                }

                // Check the event ID is valid
                if (!students.checkEventId(arguments.get("-class"))) {
                    System.out.println("Invalid Class ID. Class not found.");
                    System.exit(1);
                }

                // Enroll student
                if (arguments.size() == 4)
                    students.enrollStudent(arguments.get("-student"), arguments.get("-class"));

                // Remove enrollment TODO

            }

            // Show student marks TODO
            if (arguments.containsKey("-marks") && arguments.size() == 2) {

            }

            // Add/Remove student marks TODO
            if (arguments.containsKey("-narks") && arguments.size() == 4 || arguments.size() == 5) {

            }
        }


        /**
         * Teachers and its options
         */
        if (arguments.containsKey("-teachers")) {

            // Display all teachers TODO
            if (arguments.size() == 1) {

            }

            // Display specific teacher TODO
            if (arguments.containsKey("-id") && arguments.size() == 2) {

            }

            // Create a new teacher TODO
            if (arguments.containsKey("-create") && arguments.size() == 6) {

            }

            // Delete a teacher TODO
            if (arguments.containsKey("-delete") && arguments.size() == 2) {

            }

            // Bind teacher to course (or remove) TODO
            if (arguments.containsKey("-bind") && arguments.size() == 4 || arguments.size() == 5) {

            }
        }
    }
}
