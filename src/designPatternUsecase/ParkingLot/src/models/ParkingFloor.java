package designPatternUsecase.ParkingLot.src.models;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingFloor {
    private final int parkingFloorId;
    private final Map<VehicleType, Integer> perVehicleTypeCurrentOccupiedCount;
    private final Map<VehicleType, List<ParkingSpot>> perVehicleTypeParkingSpot;

    public ParkingFloor(int parkingFloorId){
        this.parkingFloorId = parkingFloorId;
        this.perVehicleTypeCurrentOccupiedCount = new HashMap<>();
        this.perVehicleTypeParkingSpot = new HashMap<>();
    }

    public int getParkingFloorId(){
        return this.parkingFloorId;
    }

    // Assigns new parking spots for vehicle types
    public void addParkingSpotForVehicleType(VehicleType vehicleType, int totalSpots){
        perVehicleTypeCurrentOccupiedCount.putIfAbsent(vehicleType, 0);
        perVehicleTypeParkingSpot.putIfAbsent(vehicleType, new ArrayList<>());
        addSpots(vehicleType, totalSpots);
    }

    private void addSpots(VehicleType vehicleType, int totalSpots){
        for(int i=0;i<totalSpots;i++){
            perVehicleTypeParkingSpot.get(vehicleType).add(new ParkingSpot(i,parkingFloorId, vehicleType));
        }
    }

    public ParkingSpot allocateSpot(VehicleType vehicleType) {
        List<ParkingSpot> spots = perVehicleTypeParkingSpot.get(vehicleType);

        for (ParkingSpot spot : spots) {
            if (spot.isAvailable()) {
                spot.reserveSpot();
                // Looks up for the key(vehicleType) → passes key and current value to the lambda → stores whatever the lambda returns as the new value for that key.
                perVehicleTypeCurrentOccupiedCount.compute(vehicleType, (k, v) -> v + 1);
                return spot;
            }
        }
        return null;
    }

    public void releaseSpot(ParkingSpot spot) {
        spot.unReserveSpot();
        VehicleType type = spot.getVehicleType();
        perVehicleTypeCurrentOccupiedCount.compute(type, (k, count) -> count - 1);
    }

    public String getStatus() {
        return perVehicleTypeCurrentOccupiedCount.entrySet().stream()
                .map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.joining(", "));
    }
}
