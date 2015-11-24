package com.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class LoveliveDaoGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.thoughtworks.lhli.lovelive_rock.data");

        LoveliveDaoGenerator generator = new LoveliveDaoGenerator();
        generator.addTripStation(schema);

        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTripStation(Schema schema) {
        Entity station = schema.addEntity("Station");
        station.addIdProperty();
        station.addStringProperty("name");
        station.addStringProperty("englishName");
        station.addStringProperty("code");
        station.implementsSerializable();

        Entity trip = schema.addEntity("Trip");
        trip.addIdProperty();
        trip.addDateProperty("tripDate");
        trip.addStringProperty("trainNumber").notNull();
        trip.addStringProperty("scheduledArriveTime");
        trip.addStringProperty("scheduledDepartTime");
        trip.addStringProperty("arriveTime");
        trip.addStringProperty("departTime");
        trip.addStringProperty("arriveMessage");
        trip.addStringProperty("departMessage");
        trip.addBooleanProperty("hasReminder");
        Property stationCodeProperty = trip.addLongProperty("stationId").notNull().getProperty();
        trip.addToOne(station, stationCodeProperty);
        trip.implementsSerializable();
    }
}
