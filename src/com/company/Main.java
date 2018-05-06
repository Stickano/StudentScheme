package com.company;

import models.Location;
import models.LocationHandler;

public class Main {

    public static void main(String[] args) {

        LocationHandler locations = new LocationHandler();
        for (Location location : locations.getLocations()) {
            System.out.println(location.GetLocation());
        }

    }
}
