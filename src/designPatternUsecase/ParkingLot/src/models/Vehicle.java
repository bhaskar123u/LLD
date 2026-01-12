package designPatternUsecase.ParkingLot.src.models;

// immutable record class
public record Vehicle(String vehicleNumber, VehicleType vehicleType) {
    public Vehicle {
        if (vehicleNumber == null || vehicleNumber.isBlank()) {
            throw new IllegalArgumentException("Invalid vehicle number");
        }
    }
}
