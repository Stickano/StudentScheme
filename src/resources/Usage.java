package resources;

public final class Usage {

    private Usage(){};

    public static final String Common() {
        String usage = "University of Pardubice, IT. \r\n";
        usage += "Usage: \r\n";
        usage += "  Show all schools:               -schools" + "\r\n";
        usage += "  Show specific school:               -id=\"School ID\"" + "\r\n";
        usage += "  Create new school:                  -create -location=\"Address\" -phone=\"Integer Phone Number\" -id=\"Unique School ID\"" + "\r\n";
        usage += "  Update school (Phone & Address):    -update=\"School ID\" [-location=\"Address\"] [-phone=\"Integer Phone Number\"]" + "\r\n";
        usage += "  Delete a school:                    -delete=\"School ID\"" + "\r\n";

        usage += "\r\n";
        usage += "  Show all students:              -students" + "\r\n";
        usage += "  Show specific student:              -id=\"Student ID\"" + "\r\n";
        usage += "  Create a new student:               -create -name=\"Firstname Lastname\" dob=\"Date of Birth (yyyyMMdd)\"" + "\r\n";
        usage += "  Update student (Name):              -update=\"Student ID\" -name=\"Firstname Lastname\"" + "\r\n";
        usage += "  Delete a student:                   -delete=\"Student ID\"" + "\r\n";
        usage += "  Enroll Student (Or remove):         -enroll [-remove] -student=\"Student ID\" -class=\"Event ID\"" + "\r\n";
        usage += "  Show student marks:                 -marks=\"Student ID\"" + "\r\n";
        usage += "  Give marks (Or remove):                 [-remove] -class=\"Event ID\" -mark=\"Student Mark\"" + "\r\n";

        usage += "\r\n";
        usage += "  Show all events for a school:   -events -school=\"School ID\"" + "\r\n";
        usage += "  Show specific event:                -id=\"Event ID\"" + "\r\n";
        usage += "  Create a new event:                 -create -id=\"Unique Course ID\" -day=\"0=Monday, 6=Sunday\" -info=\"Course Information\" -date=\"yyyyMMdd-yyyyMMdd\" -time=\"HHmm-HHmm\"" + "\r\n";
        usage += "  Update an event:                    -update=\"Event ID\" [-day=\"0=Monday, 6=Sunday\"] [-info=\"Course Information\"] [-date=\"yyyyMMdd-yyyyMMdd\"] [-time=\"HHmm-HHmm\"]" + "\r\n";
        usage += "  Delete an event:                    -delete=\"Event ID\"" + "\r\n";

        usage += "\r\n";
        usage += "  Show all teachers:              -teachers" + "\r\n";
        usage += "  Show specific teacher:              -id=\"Teacher ID\"" + "\r\n";
        usage += "  Create a new teacher:               -create -name=\"Firstname Lastname\" dob=\"Date of Birth (yyyyMMdd)\" -start=\"yyyyMMdd\" -salary=\"Integer Salary\"" + "\r\n";
        usage += "  Delete a teacher:                   -delete=\"Student ID\"" + "\r\n";
        usage += "  Bind teacher to event (Or remove):  -bind [-remove] -teacher=\"Teacher ID\" -class=\"Event ID\"" + "\r\n";

        return usage;
    }

}
