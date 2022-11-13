package com.gridnine.testing;

import java.util.List;
import java.util.function.Predicate;

public class FlightFilterImpl implements FlightFilter{
    @Override
    public List<Flight> doFilter(List<Flight> flights, Predicate<Flight>... filters) {
        return flights.stream().filter(flight -> {
            boolean result = true;
            for (Predicate<Flight> filter : filters) {
                result = filter.test(flight);
                if(!result) break;
            }
            return result;
        }).toList();
    }
}
