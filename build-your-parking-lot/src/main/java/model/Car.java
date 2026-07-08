package model;

import enums.SpotType;
import enums.VehicleType;

public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleType.CAR);
    }

    @Override
    public boolean canFitIn(ParkingSpot spot) {
        return spot.getSpotType() == SpotType.COMPACT
                || spot.getSpotType() == SpotType.LARGE;
    }
}
