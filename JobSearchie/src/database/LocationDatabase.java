package database;

import entities.Location;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

public class LocationDatabase extends DatabaseIO {

    public LocationDatabase() throws IOException {
        super(new File(new java.io.File(".").getCanonicalPath() + "/JobSearchie/database/location.csv"));
    }

    public Location getLocation(int id) {
        ArrayList<HashMap<String, String>> lines = super.getLines();
        HashMap<String, String> location = lines
                .stream()
                .filter(loc -> Integer.parseInt(loc.get("id")) == id)
                .collect(Collectors.toList())
            .get(0);
        return mapToLocation(location);
    }

    private Location mapToLocation(HashMap<String, String> loc) {
        Location location = new Location();
        location.setCountry(loc.get("country"));
        location.setState(loc.get("state"));
        location.setCity(loc.get("city"));
        location.setPostcode(loc.get("postcode"));
        return location;
    }
}
