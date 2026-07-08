package model;

import enums.SpotType;

public class ParkingSpot {
    private final int spotId;
    private final SpotType spotType;
    private Vehicle parkedVehicle;
    private final int floorNumber;

    public ParkingSpot(int spotId, SpotType spotType, int floorNumber) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.floorNumber = floorNumber;
    }

    public int getSpotId() {
        return spotId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public boolean isOccupied() {
        return parkedVehicle != null;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return !isOccupied() && vehicle.canFitIn(this);

    }

    public void parkVehicle(Vehicle vehicle) {
        if (isOccupied()) {
            throw new IllegalStateException("Spot is already occupied");
        }

        if (!vehicle.canFitIn(this)) {
            throw new IllegalArgumentException("Vehicle cannot fit in this spot.");
        }

        parkedVehicle = vehicle;
    }

    public Vehicle removeVehicle() {
        if (!isOccupied()) {
            throw new IllegalStateException("Spot is already empty.");
        }

        Vehicle vehicle = parkedVehicle;
        parkedVehicle = null;
        return vehicle;
    }

}
