package model;

import enums.VehicleType;

import java.time.LocalDate;

public abstract class Vehicle {
    private final String licensePlate;
    private final VehicleType vehicleType;

    public Vehicle(String licensePlate, VehicleType vehicleType) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public abstract boolean canFitIn(ParkingSpot spot);
}
