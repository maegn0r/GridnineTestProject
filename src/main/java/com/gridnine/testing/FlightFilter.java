package com.gridnine.testing;

import java.util.List;
import java.util.function.Predicate;

public interface FlightFilter {

    List<Flight> doFilter(List<Flight> flights, Predicate<Flight> ... filters);
}
