package model;

import enums.SpotType;
import enums.VehicleType;

public class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, VehicleType.TRUCK);
    }

    @Override
    public boolean canFitIn(ParkingSpot spot) {
        return spot.getSpotType() == SpotType.LARGE;
    }
}
