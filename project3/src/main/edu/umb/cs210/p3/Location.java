package edu.umb.cs210.p3;


import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

// An immutable type representing a location on Earth.
public class Location implements Comparable<Location> {
    private final String loc; // location name
    private final double lat; // latitude
    private final double lon; // longitude

    // Construct a new location given its name, latitude, and
    // longitude.
    public Location(String loc, double lat, double lon) {
        this.loc = loc;
        this.lat = lat;
        this.lon = lon;
    }

    public static double[] getCoordinates(Location x, Location y){
        double[] d = new double[4];

        // Get angles lat1, lon1, lat2, and lon2 from command line as
        // doubles in degree. Convert the angles from degree to radians.
        d[0] = Math.toRadians(x.lat);
        d[1] = Math.toRadians(x.lon);
        d[2] = Math.toRadians(y.lat);
        d[3] = Math.toRadians(y.lon);

        return d;
    }

    // The great-circle distance between this location and that.
    public double distanceTo(Location that) {
        double[] d = Location.getCoordinates(this, that);

        // call calculateGreatCircleDistance below
        return calculateGreatCircleDistance(d);
    }

    public static double calculateGreatCircleDistance(double[] d) {
        // Calculate and return great-circle distance d, using the equation given.
        return 111 * Math.toDegrees(Math.acos(  Math.sin(d[0]) * Math.sin(d[2]) +
                Math.cos(d[0]) * Math.cos(d[2]) * Math.cos(d[1] - d[3])));

    }

    // Is this location the same as that?
    public boolean equals(Object that) {
        if (that == null){
            return false;
        }

        Location targetLocation = (Location)that;

        return (this.lat == targetLocation.lat && this.lon == targetLocation.lon);
    }

    // -1, 0, or 1 depending on whether the distance of this 
    // location to the origin (Parthenon, Athens, Greece @
    // 37.971525, 23.726726) is less than, equal to, or greater
    // than the distance of that location to the origin.
    public int compareTo(Location that) {
        if (that == null){
            return 1;
        }

        Location origin = new Location("Parthenon (Athens Greece)", 37.971525, 23.726726);

        double sourceDistanceToOrigin = Location.calculateGreatCircleDistance(Location.getCoordinates(this, origin));
        double targetDistanceToOrigin = Location.calculateGreatCircleDistance(Location.getCoordinates(that, origin));

        if (sourceDistanceToOrigin < targetDistanceToOrigin){
            return -1;
        }
        if (sourceDistanceToOrigin > targetDistanceToOrigin){
            return 1;
        }

        return 0;
    }

    // A string representation of the location, in
    // "loc (lat, lon)" format.
    public String toString() {
        return String.format("%s (%s, %s)", this.loc, this.lat, this.lon);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        if (args.length !=3) args = new String[]{
                "4","40.6769","117.2319"
        };

        int rank = Integer.parseInt(args[0]);
        double lat = Double.parseDouble(args[1]);
        double lon = Double.parseDouble(args[2]);
        Location[] wonders = new Location[7];
        wonders[0] = new Location("The Great Wall of China (China)",
                40.6769, 117.2319);
        wonders[1] = new Location("Petra (Jordan)", 30.3286, 35.4419);
        wonders[2] = new Location("The Colosseum (Italy)", 41.8902, 12.4923);
        wonders[3] = new Location("Chichen Itza (Mexico)", 20.6829, -88.5686);
        wonders[4] = new Location("Machu Picchu (Peru)", -13.1633, -72.5456);
        wonders[5] = new Location("Taj Mahal (India)", 27.1750, 78.0419);
        wonders[6] = new Location("Christ the Redeemer (Brazil)",
                22.9519, -43.2106);
        Arrays.sort(wonders);
        for (Location wonder : wonders) {
            StdOut.println(wonder);
        }
        Location loc = new Location("", lat, lon);
        StdOut.println(wonders[rank].equals(loc));
    }
}
