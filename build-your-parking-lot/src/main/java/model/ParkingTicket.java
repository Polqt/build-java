package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingTicket {
    private final int ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private int fee;
    private boolean paid;


    public ParkingTicket(int ticketId, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = LocalDateTime.now();
        this.paid = false;
    }

    public int getTicketId() {
        return ticketId;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        if (fee < 0 ) {
            throw new IllegalStateException("Fee cannot be negative.");
        }

        this.fee = fee;
    }

    public void markExit() {
        exitTime = LocalDateTime.now();
    }

    public void markPaid() {
        paid = true;
    }

    public boolean isPaid() { return paid; }

    public int getDurationInHours() {
        LocalDateTime endTime = exitTime == null ? LocalDateTime.now() : exitTime;
        return (int) Math.max(1, Duration.between(entryTime, endTime).toHours());
    }
}
