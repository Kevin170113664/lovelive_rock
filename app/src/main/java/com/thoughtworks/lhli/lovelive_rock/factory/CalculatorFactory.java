package com.thoughtworks.lhli.lovelive_rock.factory;

import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class CalculatorFactory {

    private String eventEndTime;

    public CalculatorFactory() {
        this.eventEndTime = !LoveLiveApp.getInstance().getLatestEventEnd().equals("") ?
                LoveLiveApp.getInstance().getLatestEventEnd() : null;
    }

    public String getEventEndDay() {
        return eventEndTime != null ? String.format("%s", DateTime.parse(eventEndTime).getDayOfMonth()) : "0";
    }

    public String getEventEndHour() {
        return eventEndTime != null ? String.format("%s", DateTime.parse(eventEndTime).getHourOfDay() - 1) : "0";
    }

    public String getEventLastTime() {
        return eventEndTime != null ? String.format("%s", new Duration(new DateTime(), DateTime.parse(eventEndTime)).getStandardHours()) : "0";
    }
}
