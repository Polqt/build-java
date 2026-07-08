import enums.SpotType;
import model.*;
import service.FeeCalculator;
import service.ParkingLot;

import java.util.Scanner;

public class MainParkingLot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ParkingLot parkingLot = new ParkingLot();
        FeeCalculator feeCalculator = new FeeCalculator();

        parkingLot.addSpot(new ParkingSpot(1, SpotType.MOTORCYCLE, 1));
        parkingLot.addSpot(new ParkingSpot(2, SpotType.COMPACT, 1));
        parkingLot.addSpot(new ParkingSpot(3, SpotType.LARGE, 1));

        while (true) {
            System.out.println("\n===== PARKING LOT =====");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Unpark Vehicle");
            System.out.println("3. View Active Tickets");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> parkVehicle(scanner, parkingLot);
                case 2 -> unparkVehicle(scanner, parkingLot, feeCalculator);
                case 3 -> viewActiveTickets(parkingLot);
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void parkVehicle(Scanner scanner, ParkingLot parkingLot) {
        System.out.println("\nVehicle Type:");
        System.out.println("1. Car");
        System.out.println("2. Motorcycle");
        System.out.println("3. Truck");
        System.out.print("Choose: ");

        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter license plate: ");
        String licensePlate = scanner.nextLine();

        Vehicle vehicle;

        switch (type) {
            case 1 -> vehicle = new Car(licensePlate);
            case 2 -> vehicle = new Motorcycle(licensePlate);
            case 3 -> vehicle = new Truck(licensePlate);
            default -> {
                System.out.println("Invalid vehicle type.");
                return;
            }
        }

        try {
            ParkingTicket ticket = parkingLot.parkVehicle(vehicle);
            System.out.println("Vehicle parked successfully.");
            System.out.println("Ticket ID: " + ticket.getTicketId());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void unparkVehicle(
            Scanner scanner,
            ParkingLot parkingLot,
            FeeCalculator feeCalculator
    ) {
        System.out.print("\nEnter ticket ID: ");
        int ticketId = scanner.nextInt();

        ParkingTicket ticket = findTicketById(parkingLot, ticketId);

        if (ticket == null) {
            System.out.println("Ticket not found.");
            return;
        }

        parkingLot.unparkVehicle(ticket);

        int fee = feeCalculator.calculateFee(ticket);
        ticket.setFee(fee);
        ticket.markPaid();

        System.out.println("Vehicle unparked successfully.");
        System.out.println("Fee: ₱" + ticket.getFee());
        System.out.println("Paid: " + ticket.isPaid());
    }

    private static ParkingTicket findTicketById(ParkingLot parkingLot, int ticketId) {
        for (ParkingTicket ticket : parkingLot.getActiveTickets()) {
            if (ticket.getTicketId() == ticketId) {
                return ticket;
            }
        }

        return null;
    }

    private static void viewActiveTickets(ParkingLot parkingLot) {
        if (parkingLot.getActiveTickets().isEmpty()) {
            System.out.println("No active tickets.");
            return;
        }

        for (ParkingTicket ticket : parkingLot.getActiveTickets()) {
            System.out.println(
                    "Ticket #" + ticket.getTicketId()
                            + " | Vehicle: " + ticket.getVehicle().getLicensePlate()
                            + " | Type: " + ticket.getVehicle().getVehicleType()
                            + " | Spot: " + ticket.getParkingSpot().getSpotId()
                            + " | Spot Type: " + ticket.getParkingSpot().getSpotType()
            );
        }
    }
}