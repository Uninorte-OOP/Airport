package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;

public class FlightController {

    public static Response createFlight(String id, String plane, String departureLocation,
            String scaleLocation, String arrivalLocation, String departureDate, String hoursDurationArrival,
            String minutesDurationArrival, String hoursDurationScale, String minutesDurationScale) {

        try {

        } catch (Exception e) {
            return new Response("Unexpected error ", Status.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
