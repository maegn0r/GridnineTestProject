package com.gridnine.testing;


import java.util.List;
import static com.gridnine.testing.FilterConditions.getConditionByKey;

/* Модуль, который будет заниматься фильтрацией набора перелётов согласно различным правилам постарался сделать хорошо масштабируемым, чтобы он соответствовал
условию задачи, согласно которому правил фильтрации может быть очень много, наборы перелётов могут быть очень большими и правила могут выбираться и задаваться
динамически в зависимости от контекста выполнения операции фильтрации.

Во время выполнения задания возник момент, который несколько смутил меня. FlightBuilder создает полет, в котором время ожидания в аэропорту
ровно два часа (не более). Поскольку этот полет генерировался в месте, которое было помечено как "Another flight with more than two hours ground time",
я решил добавить проверку, которая исключит и его тоже, но на самом деле не уверен, что это на 100% соответствует условию задачи.

В конце добавил все фильтры одновременно, чтобы получить только "нормальные" полеты.
 */

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
