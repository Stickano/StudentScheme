# StudentScheme
Introduction to Java; Final Project. Pardubice CZ.

### What's this? 
This, my good sir or madam, is the final product to a `Introduction to Java` course that I took while studying in Czech. 

The requirements where to build a CLI application that could handle students and their class enrollments - So basically CRUD to CSV documents. I decided to add Schools (Buildings) and Teachers to the mix - It seemed highly relevant at the time. Though, as the closing evaluation date is nearing, it seems highly unnecessary.
<br><br>

### Table of Content
* [Missing Requirements](#whats-missing)

* [Documentation](#documentation-for-the-working-part)
   * [Alias](#Do-yourself-a-favor-and-create-an-alias)
   
   * [Usage Information](#usage-information)
   
   * [Schools](#schools)
      * [All schools](#display-all-schools)
      * [Display school w/ ID](#display-school-from-id)
      * [Create school](#create-new-school)
   
   * [Events/Course](#events-courses)
      * [All events](#show-all-events)
      * [Display event w/ ID](#display-event-from-id)
      * [Create event](#create-event)
      
   * [Students](#students)
      * [All students](#show-all-students)

<br><br>
### What's missing?
I imagine that more documentation will be added as the functionality will come along.

1. #### School
  - Delete Schools
  - Update Schools (Will probably be done with Delete/Create methods)
  - ~~Create School~~
     - Give feedback

2. #### Course (Event)
  - Delete Events
  - Update Events 
  - ~~Create Event~~
     - Give feedback
  
3. #### Student
  - Show student from ID
  - Create new student
  - Update students
  - Delete students
  - Enroll/Remove Enrollment to/from course
  - Show student marks
  - Add/Remove student marks
  
4. #### Teacher
  - Show all teachers
  - Show teacher from ID
  - Create new teacher
  - Delete teachers
  - Assign/Remove teacher to course 

<br><br>
# Documentation for the working part! 
Where the fun begins. A few pointers and usage information for the existing/working code.
<br>
### Do yourself a favor and create an `alias`
This is not a running application, but instead a CLI application that takes one argument at the time. When we run this application we have to include the included libraries and models, which adds to the command you have to write. Having to write the full command to run this java application would look something like; `java -cp "opencsv-4.1.jar:commons-lang3-3.7.jar:." com.company.Main -arg1 -arg2`which can be pretty tedious. 

On your glorious linux system, though, you are able to quickly create an alias for such tedious commands! Following the rest of this guide will assume that the command `ssch` will fire up the application through the alias. 

##### ssch (Student Scheme) 
Note the trailing space which will allow you to pass along arguments. <br>
Also note and change the path in first command (`cd`) to the location of the project.

```
alias ssch='cd path/to/project/out/production/StudentScheme ; java -cp "opencsv-4.1.jar:commons-lang3-3.7.jar:." com.company.Main '
```
<br><br>
## Usage Information
Use `-h` or `--help` to get a list of available options.

```
$ ssch --help
University of Pardubice, IT. 
Usage: 
  Show all schools:               -schools
  Show specific school:               -id="School ID"
  Create new school:                  -create -location="Address" -phone="Integer Phone Number" -id="Unique School ID"
  Update school (Phone & Address):    -update="School ID" [-location="Address"] [-phone="Integer Phone Number"]
  Delete a school:                    -delete="School ID"

  Show all students:              -students
  Show specific student:              -id="Student ID"
  Create a new student:               -create -name="Firstname Lastname" dob="Date of Birth (yyyyMMdd)"
  Update student (Name):              -update="Student ID" -name="Firstname Lastname"
  Delete a student:                   -delete="Student ID"
  Enroll Student (Or remove):         -enroll [-remove] -student="Student ID" -class="Event ID"
  Show student marks:                 -marks="Student ID"
  Give marks (Or remove):                 [-remove] -class="Event ID" -mark="Student Mark"

  Show all events for a school:   -events -school="School ID"
  Show specific event:                -id="Event ID"
  Create a new event:                 -create -id="Unique Course ID" -day="0=Monday, 6=Sunday" -info="Course Information" -date="yyyyMMdd-yyyyMMdd" -time="HHmm-HHmm"
  Update an event:                    -update="Event ID" [-day="0=Monday, 6=Sunday"] [-info="Course Information"] [-date="yyyyMMdd-yyyyMMdd"] [-time="HHmm-HHmm"]
  Delete an event:                    -delete="Event ID"

  Show all teachers:              -teachers
  Show specific teacher:              -id="Teacher ID"
  Create a new teacher:               -create -name="Firstname Lastname" dob="Date of Birth (yyyyMMdd)" -start="yyyyMMdd" -salary="Integer Salary"
  Delete a teacher:                   -delete="Student ID"
  Bind teacher to event (Or remove):  -bind [-remove] -teacher="Teacher ID" -class="Event ID"

```
<br><br>
## Schools
The `-schools` argument is one of the main arguments. Only 1 main argument can be passed at any time. 

### Display all schools
If you don't pass along a `-id` argument, all schools will be showned. <br>
Each school consists of a ID, location and phonenumber.

```
$ ssch -schools

cs02
Another Address, Same Town, 12 345
65434567

c301
and the last location, with commas and shit
12365454
```


### Display school from ID
Use `-id="[School ID]"` to show specific school.

```
$ ssch -schools -id="cs02"

cs02
Another Address, Same Town, 12 345
65434567
```


### Create new school
Use `-create` with the 3 additional required arguments; `-id`, `-location` and `-phone`.

```
$ ssch -schools -create -id="cs505" -location="New Location" -phone="12345678"

$ ssch -schools -id="cs505"                                                   

cs505
New Location
12345678
```

<br><br>
## Events (Courses)
The `-events` is a main argument. The events argument requires a school id to passed along; `-school="[School ID]"`. 


The events has the following attributes; `id`, `description`, `startDate` - `endDate`, `dayOfWeek` and `startTime` - `endTime` <br>
Where the start/end date is the course length, day of week is which day in each week the course is held (we assume), start/end time is which hour of the day the course is held.

### Show all events
Leave out the `-id` to get a week-day schedule that shows all events for the chosen school. In this example, there is only classes on Wednesdays for this school location.

```
$ ssch -events -school="cs02"

Monday

Tuesday

Wednesday
    JA105
    Feb 15 2018 - Jun 01 2018
    1300 - 1500
        Introduction to Algorithms

    JA106
    Feb 15 2018 - Jun 01 2018
    1300 - 1500
        Introduction to C#

    JA107
    Feb 15 2018 - Jun 01 2018
    1300 - 1500
        Introduction to Web Development

    JA112
    Feb 15 2018 - Jun 01 2018
    1300 - 1500
        Introduction to Trolling

    JA113
    Feb 15 2018 - Jun 01 2018
    1300 - 1500
        Introduction to UML


Thursday

Friday

Saturday

Sunday
```


### Display event from ID
Use `-id="[Event ID]"` to show specific event/course.

```
$ ssch -events -school="cs02" -id="JA105"

JA105
Feb 15 2018 - Jun 01 2018
1300 - 1500
    Introduction to Algorithms
```


### Create event
Use `-create` to add new events. Additional 5 arguments are required; `-id`, `-day`, `-time`, `-date` and `-info`.

The id has to be unique. <br
The day can be an integer value between 0-6, where 0 is Monday and 6 is Sunday.<br>
Time is start/end time seperated by a `-` (minus) in `HHmm` format, ie. from 13:00 to 15:00; `-time="1300-1500"`.<br>
Date is start/end date seperated by a `-` in `yyyyMMdd` format, ie. from January 10 to June 10, 2018; `-date="20180110-20180610"`.<br>
And info is a short, informative description of the event/course.

```
$ ssch -events -school="cs02" -create -id="dc10" -day="0" -info="This brand new Course" -date="20180110-20180610" -time="1300-1500"

$ ssch -events -school="cs02" -id="dc10"

dc10
Jan 10 2018 - Jun 10 2018
1300 - 1500
    This brand new Course
```

<br><br>
## Students
The `-students` argument is a main argument.


### Show all students
Leave out other arguments to list all the students.<br>
At the point of writing (well a lot) of this readme file, this is actually the point I have gotten to in my code as well. Having run this example I can see that a little adjusting is needed. The hour is close to 3am at this time though, and my bed really do seem more preferrable over debugging at this point. #TODO:

```
$ ssch -students

Firstname Lastname
ID: Fri Jan 15 00:00:00 CET 1988firstnamelastname

Anders Breivik
ID: Sat Mar 06 00:00:00 CET 1982andersbreivik

Poul Erik
ID: Tue Mar 12 00:00:00 CET 1991poulerik

```
