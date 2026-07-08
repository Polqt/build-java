package service;

import model.ParkingTicket;
import model.Vehicle;

public class FeeCalculator {
    // Hourly Ratee
    private static final int MOTORCYCLE_RATE = 20;
    private static final int CAR_RATE = 40;
    private static final int TRUCK_RATE = 80;

    public int calculateFee(ParkingTicket ticket) {
        Vehicle vehicle = ticket.getVehicle();
        int durationInHours = ticket.getDurationInHours();
        return durationInHours * getHourlyRate(vehicle);
    }

    private int getHourlyRate(Vehicle vehicle) {
        return switch (vehicle.getVehicleType()) {
            case MOTORCYCLE -> MOTORCYCLE_RATE;
            case CAR -> CAR_RATE;
            case TRUCK -> TRUCK_RATE;
        };
    }
}
