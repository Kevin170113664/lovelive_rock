package com.thoughtworks.lhli.lovelive_rock.bus;

public class FilterEvent {
    public Integer filterKey;
    public String filterValue;

    public FilterEvent(Integer filterKey, String filterValue) {
        this.filterKey = filterKey;
        this.filterValue = filterValue;
    }

    public Integer getFilterKey() {
        return filterKey;
    }

    public String getFilterValue() {
        return filterValue;
    }
}
