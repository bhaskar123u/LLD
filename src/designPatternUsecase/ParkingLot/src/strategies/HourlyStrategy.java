package designPatternUsecase.ParkingLot.src.strategies;

import designPatternUsecase.ParkingLot.src.models.ParkingTicket;
import designPatternUsecase.ParkingLot.src.models.Vehicle;
import designPatternUsecase.ParkingLot.src.models.VehicleType;

// Fixed price for 1st 2 hours, then half base price for vehicle type every extra hour
public class HourlyStrategy implements PaymentStrategy {
    @Override
    public double getFinalPrice(ParkingTicket parkingTicket) {
        Vehicle vehicle = parkingTicket.getVehicle();
        int startHour = parkingTicket.getTicketStartTime().getHour();
        int endHour = parkingTicket.getTicketEndTime().getHour();

        return calculatePrice(vehicle, startHour, endHour);
    }

    public double calculatePrice(Vehicle vehicle, int startHour, int endHour) {
        double base = getFixedPricePerVehicle(vehicle.vehicleType());
        int parked = endHour - startHour;
        if (parked <= 2) return base;
        return base + (parked - 2) * (base / 2);
    }

    public int getFixedPricePerVehicle(VehicleType vehicleType){
        switch (vehicleType){
            case TWO_WHEELER -> {
                return 30;
            }
            case FOUR_WHEELER -> {
                return 50;
            }
        }
        return 0;
    }
}
