package com.gridnine.testing;


import java.util.List;
import static com.gridnine.testing.FilterConditions.getConditionByKey;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println(flights);
        System.out.println("____________________________________________________");

        FlightFilter flightFilter = new FlightFilterImpl();
        List<Flight> flights1 = flightFilter.doFilter(flights, getConditionByKey(ConditionKey.ARRIVE_BEFORE_NOW));
        System.out.println("Исключены: " + ConditionKey.ARRIVE_BEFORE_NOW.getLabel() + " " + flights1);

        System.out.println("____________________________________________________");
        List<Flight> flights2 = flightFilter.doFilter(flights, getConditionByKey(ConditionKey.SEGMENTS_WITH_DATE_ARR_LESS_DATE_DEP));
        System.out.println("Исключены: " +ConditionKey.SEGMENTS_WITH_DATE_ARR_LESS_DATE_DEP.getLabel() + " " + flights2);

        System.out.println("____________________________________________________");
        List<Flight> flights3 = flightFilter.doFilter(flights, getConditionByKey(ConditionKey.WAITING_ON_LAND_MORE_TWO_HOURS));
        System.out.println("Исключены: " +ConditionKey.WAITING_ON_LAND_MORE_TWO_HOURS.getLabel() + " " + flights3);

        System.out.println("____________________________________________________");
        List<Flight> allFilters = flightFilter.doFilter(flights, getConditionByKey(ConditionKey.WAITING_ON_LAND_MORE_TWO_HOURS)
                , getConditionByKey(ConditionKey.ARRIVE_BEFORE_NOW), getConditionByKey(ConditionKey.SEGMENTS_WITH_DATE_ARR_LESS_DATE_DEP));
        System.out.println("По всем фильтрам: " + allFilters);
    }
}
