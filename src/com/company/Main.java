package com.company;

import models.Location;
import models.LocationHandler;
import resources.Parser;
import resources.Usage;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // Print the usage options (--help)
        if (args.length==0 || args[0].equals("-h") || args[0].equals("--help"))
            System.out.println(Usage.Common());

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
            System.out.println("Use --help for usage information");
            System.exit(1);
        }

        // Print all schools
        if (args.length == 1 && args[0].toLowerCase().equals("-schools")){
            LocationHandler locations = new LocationHandler();
            for (Location location : locations.getLocations()) {
                System.out.println();
                System.out.println(location.GetId());
                System.out.println(location.GetLocation());
                System.out.println(location.GetPhone());
            }
        }


    }
}
