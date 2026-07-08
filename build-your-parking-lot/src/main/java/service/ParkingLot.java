package service;

import exception.ParkingFullException;
import model.ParkingSpot;
import model.ParkingTicket;
import model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final List<ParkingSpot> spots = new ArrayList<>();
    private final List<ParkingTicket> activeTickets = new ArrayList<>();
    private int nextTicketId = 1;

    public void addSpot(ParkingSpot parkingSpot) {
        spots.add(parkingSpot);
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findAvailableSpot(vehicle);

        if (spot == null) throw new ParkingFullException("No available spot for " + vehicle.getVehicleType());

        spot.parkVehicle(vehicle);

        ParkingTicket ticket = new ParkingTicket(nextTicketId++, vehicle, spot);
        activeTickets.add(ticket);
        return ticket;
    }

    public void unparkVehicle(ParkingTicket ticket) {
        ticket.getParkingSpot().removeVehicle();
        ticket.markExit();
        activeTickets.remove(ticket);
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle) {
        for (ParkingSpot spot: spots) {
            if (spot.canFitVehicle(vehicle)) {
                return spot;
            }
        }
        return null;
    }

    public List<ParkingSpot> getSpots() {
        return List.copyOf(spots);
    }

    public List<ParkingTicket> getActiveTickets() {
        return List.copyOf(activeTickets);
    }
}
