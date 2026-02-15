package com.gradescope.hw5;//DO NOT REMOVE PACKAGE LINE
//Without this line authograder will not run correctly
//You can comment it while you work on the problem
//When everything works - uncomment and submit!
//package com.gradescope.hw5;

/*Header
/HW 5
/Name:Samuel Johnson
AI Use Disclaimer
ChatGPT used to diagnose bugs in code for faster debugging
*/
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Query {
    public static int Query0(Iterable<FlightRecord> input) {
        int sum = 0;

        // There are two common methods of iterating over an Iterable

        // 1. Use the Iterator from the Iterable
        //Uncomment to see fow it works

        Iterator<FlightRecord> it = input.iterator();
        while (it.hasNext()) {
            FlightRecord r = it.next();
                if ("LAX".equals(r.ORIGIN) && "ORD".equals(r.DEST) && r.MONTH == 8) {
                    sum++;
                }
            }



        // 2. Use Java's "for each" syntax.
        // Read this as "for each FlightRecord r from input"
        //for (FlightRecord r : input) {
         //   if (r.ORIGIN.equals("LAX") && r.DEST.equals("ORD") && r.MONTH == 8) {
          //      sum++;
          //  }
       // }
        return sum;
    }

    public static int Query1(Iterable<FlightRecord> input) {
        int sum = 0;
        Iterator<FlightRecord> it = input.iterator();
        while (it.hasNext()) {
            FlightRecord r = it.next(); // loops through the flights, if the origin is equal to Cedar rapids
            if (r.ORIGIN.equals("CID")) { // it adds to the sum and returns the sum
                sum++;
            }
        }
        return sum;
    }

    public static Iterable<String> Query2(Iterable<FlightRecord> input) {
        HashSet<String> set = new HashSet<>(); // initializes a hashset
        Iterator<FlightRecord> it = input.iterator();
        for (FlightRecord r : input) { // if the origin is in cedar rapids
            if (r.ORIGIN.equals("CID")) { // it adds the destination and state abbreviation to set
                set.add( r.DEST + ", " + r.DEST_STATE_ABR );
            }// returns the set once the loop is concluded
        }
        return set;
    }


    public static int Query3(Iterable<FlightRecord> input) {
        HashSet<String> seen = new HashSet<>(); // a hashset to keep track of seen destinations
        Iterator<FlightRecord> it = input.iterator(); // iterator to move through data
        int sum = 0; // initializes all those who come from Cedar Rapids
        while (it.hasNext()) {
            FlightRecord r = it.next(); // if there is an origin from Cedar rapids and its not been seen
            if (r.ORIGIN.equals("CID") && !seen.contains(r.DEST)) {
                seen.add(r.DEST); // destination gets added to seen and the sum goes up
                sum++;
            }
        }
        return sum; // at the end of the loop, it returns the sum
    }

    public static Iterable<String> Query4(Iterable<FlightRecord> input) {
        HashMap<String, Integer> map = new HashMap<>(); // initializtions
        HashSet<String> values = new HashSet<>();
        Iterator<FlightRecord> it = input.iterator();
        while (it.hasNext()) {
            FlightRecord r = it.next(); // if origin CID and map doesn't have destination
            if (r.ORIGIN.equals("CID") && !map.containsKey(r.DEST)) {
                map.put(r.DEST, 1); // it adds the destination to the map
            } else if (r.ORIGIN.equals("CID") && map.containsKey(r.DEST)) {
                map.put(r.DEST, map.get(r.DEST) + 1); // otherwise it adds one to the map
            }
        }for(String dest: map.keySet()) {
            values.add(dest+"="+map.get(dest)); // returns the destination and the number of flights out of cedar rapids
        }
        return values;
    }

    public static String Query5(Iterable<FlightRecord> input) {
        HashMap<Integer, Integer> map = new HashMap<>(); // creates new hashmap
        Iterator<FlightRecord> it = input.iterator(); // creates an iterator through the data
        while (it.hasNext()) { // while there is still another value in the data
            FlightRecord r = it.next(); // keeps track of the next value
            map.put(r.MONTH, map.getOrDefault(r.MONTH, 0) + 1);
        }
        int max = 0; // sets the max and corresponding month number
        int monthe = 0;
        for (Integer month : map.keySet()) {
            if (max < map.get(month)) { // loops through to find the max month and value
                max = map.get(month);
                monthe = month;
            } // returns it at the end
        }
        return monthe + " had " + max + " flights";
    }

    public static String Query6(Iterable<FlightRecord> input) {
        HashMap<String, Integer> map = new HashMap<>(); // creates new hashmap
        for (FlightRecord r : input) { // establishes variables for the origin and dest state
            String dest = r.DEST_STATE_ABR;
            String orig = r.ORIGIN_STATE_ABR; // puts it in alphanumeric order
            String key = (orig.compareTo(dest) < 0) ? "(" + orig + "," + dest + ")" : "(" + dest + "," + orig + ")";
            map.put(key, map.getOrDefault(key, 0) + 1); //
        }
        String bestPair = "";
        int maxCount = 0;
        for (String pair : map.keySet()) {
            if (map.get(pair) > maxCount) { // returns the states with the most flights between them
                maxCount = map.get(pair);
                bestPair = pair;
            }
        }
        return bestPair;
    }

    public static Iterable<String> Query7(Iterable<FlightRecord> input) {
        HashSet<String> set = new HashSet<>(); // these are just points that come from IA
        HashSet<String> seen = new HashSet<>(); // this includes all of the points
        Iterator<FlightRecord> it = input.iterator();
        while (it.hasNext()) {
            FlightRecord r = it.next();
            seen.add(r.DEST_STATE_ABR);
            if(r.ORIGIN_STATE_ABR.equals("IA")) { // if the flight origin is from IA
                set.add(r.DEST_STATE_ABR); // it adds the corresponding destination to the set
            }
        }
        seen.removeAll(set); // removes the elements in seen that are in set
        return seen; // returns remaining states
    }
        public static Iterable<String> Query8 (Iterable < FlightRecord > input) {
        HashMap<String, Integer> intra = new HashMap<>(); // initialization of maps and sets
        HashMap<String, Integer> total = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for (FlightRecord r : input) { // puts all the flights from that state into a map
            total.put(r.ORIGIN_STATE_ABR, total.getOrDefault(r.ORIGIN_STATE_ABR,0)+1);
            if(r.ORIGIN_STATE_ABR.equals(r.DEST_STATE_ABR)) { // a map with number of instate flights
                intra.put(r.DEST_STATE_ABR,intra.getOrDefault(r.DEST_STATE_ABR,0) + 1);
            }
                }DecimalFormat df = new DecimalFormat("#.000"); // properly formats output
        for(String state: intra.keySet()) {
            int totalFlights=total.get(state);
            int intraFlights=intra.getOrDefault(state,0);
            double ratio=(double)intraFlights/totalFlights; // creates correct ratio
            String stratio=df.format(ratio);
            set.add(state+"="+stratio); // adds ratio to the set and returns it
            }
            return set;
        }
        public static Iterable<String> Query9 (Iterable < FlightRecord > input) {
            HashMap<String,HashMap<String,Integer>> stateAirlineCounts = new HashMap<>();
            HashSet<String> set = new HashSet<>();
            for (FlightRecord r : input) {
                stateAirlineCounts.putIfAbsent(r.DEST_STATE_ABR, new HashMap<>());
                HashMap<String,Integer> airlineCounts = stateAirlineCounts.get(r.DEST_STATE_ABR);
                airlineCounts.put(r.UNIQUE_CARRIER_NAME, airlineCounts.getOrDefault(r.UNIQUE_CARRIER_NAME, 0) + 1);
            }for(String state: stateAirlineCounts.keySet()) {
                HashMap<String,Integer> airlineCounts = stateAirlineCounts.get(state);
                String maxAirline=null;
                int maxFlights=0;
                for (String airlineName : airlineCounts.keySet()) {
                    int count=airlineCounts.get(airlineName);
                    if(count>maxFlights) {
                        maxFlights=count;
                        maxAirline=airlineName;
                    }
                }
                set.add(state+","+maxAirline);
            }

            return set;
            }

        public static Iterable<String> Query10 (Iterable < FlightRecord > input) {
           HashSet<String> routes=new HashSet<>();
           HashSet<String> middle=new HashSet<>();
           HashSet<String> toLAX=new HashSet<>();
           for(FlightRecord r : input) { // if the origin is in cedar rapids, it adds the Destination to middle
               if(r.ORIGIN.equals("CID")){
                   middle.add(r.DEST);
               }
           }
           for(FlightRecord r : input) {
               if(r.DEST.equals("LAX")){ // if destination is in LAX, it is added to the to LAX Set
                   toLAX.add(r.ORIGIN);
               }
           }
           for(String layover:middle){
               if(toLAX.contains(layover)){ // if there is a destination within the toLAX set, its added as a combination
                   routes.add("CID->"+layover+"->LAX");
               }
           }
           return routes; // returns the set with the layover combinations from CID to LAX
        }

    public static void main(String[] args) throws IOException {
        //This will run Query0 provided by us on flights1990.csv
        Iterable<FlightRecord> input = DataImporter.getData("flights/flights1990.csv");
        Iterable<String> results = Query10(input);
        System.out.print(results);
    }
}
