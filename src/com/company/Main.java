package com.company;

import models.Location;
import models.LocationHandler;
import resources.Parser;
import resources.Usage;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

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
         * This is all very static and a LOT of refactoring would be needed
         * if the parameters should ever change - Relevant for all of the major categories.
         */
        if (arguments.containsKey("-schools")){

            LocationHandler locations = new LocationHandler();

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
    }
}
