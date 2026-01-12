package designPatternUsecase.ParkingLot.src.controllers;

import designPatternUsecase.ParkingLot.src.models.ParkingSpot;
import designPatternUsecase.ParkingLot.src.models.ParkingTicket;
import designPatternUsecase.ParkingLot.src.models.Vehicle;

public class ParkingTicketGenerator {
    public ParkingTicket generateParkingTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        return new ParkingTicket(vehicle, parkingSpot);
    }
}
