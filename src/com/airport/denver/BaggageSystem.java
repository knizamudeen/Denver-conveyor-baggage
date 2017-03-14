package com.airport.denver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.airport.denver.helper.FileLoadUtility;
import com.airport.denver.model.Baggage;
import com.airport.denver.model.ConveyorRoute;
import com.airport.denver.model.Departure;
import com.airport.denver.model.TraversePath;

/**
 * Created by Nizam on 3/2/2017.
 */
public class BaggageSystem {

    private Map<String, List<ConveyorRoute>> conveyorBeltInfo;
    private Map<String, Baggage> baggages;
    private Map<String, Departure> departures;

    private List<String> traversePath = new ArrayList<>();
    private Map<String, TraversePath> traverseDetails = new HashMap<>();

    private boolean routeFound;

    public static void main(String args[]) {

        BaggageSystem baggageSystem = new BaggageSystem();
        baggageSystem.initialize();
        baggageSystem.deliverBags();

    }

    public void deliverBags() {

        /**
         * Iterating list of baggage for delivery & determining source and destination for bags
         */
        Iterator<String> bag_ids = baggages.keySet().iterator();

        while (bag_ids.hasNext()) {

            String currentBagID = bag_ids.next();

            Baggage baggage = baggages.get(currentBagID);

            String entryPoint = baggage.getEntry_point();

            String destinationFlightID = baggage.getFlight_id();

            String destinationGate;

            if ("ARRIVAL".equalsIgnoreCase(destinationFlightID)) {
                destinationGate = "Baggage_claim";
            } else {
                Departure departureInfo = departures.get(destinationFlightID);
                destinationGate = departureInfo.getFlight_gate();
            }

            String source = entryPoint;
            traverseDetails.put(source, new TraversePath(null, 0));

            findShortestPath(entryPoint, destinationGate);

            System.out.print(currentBagID + " ");
            if (routeFound) {
                traversePath = getRoute(destinationGate);
                Collections.reverse(traversePath);
                for (String val : traversePath) {
                    System.out.print(val + " ");
                }
                System.out.print(destinationGate + " ");
                TraversePath tp = traverseDetails.get(destinationGate);
                System.out.println(": " + tp.getTime());
            } else {
                System.out.println("no routes found");
            }

            // System.out.println(traverseDetails);
            traverseDetails.clear();
            traversePath.clear();
            routeFound = false;

        }


    }

    /***
     * initiaizes baggage system by reading inputs from files
     */
    public void initialize() {

        /**
         * reading data from 3 different CSV files
         */
        conveyorBeltInfo = FileLoadUtility.readConveyorBeltInfo();

        baggages = FileLoadUtility.readBaggageInfo();

        departures = FileLoadUtility.readDepartureInfo();

    }

    /**
     * Gets the best path available for traversal
     *
     * @param node
     * @return
     */
    public List<String> getRoute(String node) {
        TraversePath tp = traverseDetails.get(node);
        if (tp != null && tp.getParentNode() != null) {
            traversePath.add(tp.getParentNode());
            getRoute(tp.getParentNode());
        }
        return traversePath;
    }

    public void findShortestPath(String entryPoint, String destinationGate) {

        if (entryPoint.equalsIgnoreCase(destinationGate)) {
            routeFound = true;
        }
        List<ConveyorRoute> conveyorRoutes = conveyorBeltInfo.get(entryPoint);

        if (conveyorRoutes != null) {
            for (ConveyorRoute conveyorRoute : conveyorRoutes) {
                int timeTaken = traverseDetails.get(entryPoint).getTime() + conveyorRoute.getTime();
                TraversePath tp = traverseDetails.get(conveyorRoute.getDestination());
                if ((tp != null && (timeTaken < tp.getTime())) || (tp == null)) {
                    traverseDetails.put(conveyorRoute.getDestination(), new TraversePath(entryPoint, timeTaken));
                }
                findShortestPath(conveyorRoute.getDestination(), destinationGate);
            }
        }
    }
}