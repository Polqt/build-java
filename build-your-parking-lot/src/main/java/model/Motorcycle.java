package model;

import enums.SpotType;
import enums.VehicleType;

public class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleType.MOTORCYCLE);
    }

    @Override
    public boolean canFitIn(ParkingSpot spot) {
        return spot.getSpotType() == SpotType.MOTORCYCLE
                || spot.getSpotType() == SpotType.COMPACT
                || spot.getSpotType() == SpotType.LARGE;
    }

}
