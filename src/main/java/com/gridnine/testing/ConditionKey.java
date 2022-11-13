package com.gridnine.testing;

public enum ConditionKey {
    ARRIVE_BEFORE_NOW("Вылет до текущего момента времени"),
    SEGMENTS_WITH_DATE_ARR_LESS_DATE_DEP("Cегменты с датой прилёта раньше даты вылета"),
    WAITING_ON_LAND_MORE_TWO_HOURS("Общее время, проведённое на земле превышает два часа");


    private String label;

    public String getLabel() {
        return label;
    }

    ConditionKey(String label) {
        this.label = label;
    }
}
