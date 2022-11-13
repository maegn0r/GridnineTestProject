package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class FilterConditions {

    private static final Map<ConditionKey, Predicate<Flight>> conditions = new HashMap<>();

    static {
        fillPredicate();
    }


    private static void fillPredicate() {
        conditions.put(ConditionKey.ARRIVE_BEFORE_NOW, flight -> !flight.getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now()));
        conditions.put(ConditionKey.SEGMENTS_WITH_DATE_ARR_LESS_DATE_DEP, flight -> !flight.getSegments().stream().anyMatch(segment -> segment.getDepartureDate().isAfter(segment.getArrivalDate())));
        conditions.put(ConditionKey.WAITING_ON_LAND_MORE_TWO_HOURS, flight -> {
            List<Segment> segments = flight.getSegments();
            boolean flag = false;
            for (int i = 0; i < segments.size() - 1; i++) {
                flag = segments.get(i).getArrivalDate().plusHours(2L).isEqual(segments.get(i + 1).getDepartureDate())
                        || segments.get(i).getArrivalDate().plusHours(2L).isBefore(segments.get(i + 1).getDepartureDate());
                if(flag) break;
            }
            return !flag;
        });
    }

    public static Predicate<Flight> getConditionByKey(ConditionKey conditionKey){
        return conditions.get(conditionKey);
    }

}
