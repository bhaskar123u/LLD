package designPatternUsecase.ParkingLot.src;

import designPatternUsecase.ParkingLot.src.models.*;

/*
Assume -> Single entry, Single exit to parking lot
*/
public class ParkingLotApplication {
    private final ParkingLot parkingLot;

    ParkingLotApplication(){
        parkingLot = new ParkingLot("PVR Parking Lot");
    }

    public ParkingLot getParkingLot(){
        return parkingLot;
    }

    public static void main(String[] args) {
        ParkingLotApplication parkingLotApplication = new ParkingLotApplication();
        parkingLotApplication.init(2,50,50);
        Vehicle vehicle = new Vehicle("KA03MB3852",VehicleType.TWO_WHEELER);

        // Vehicle enter
        ParkingTicket parkingTicket = parkingLotApplication.getParkingLot().getParkingTicket(vehicle);
        if(parkingTicket != null)
            System.out.println("[New Parking Ticket] : Welcome "+vehicle.vehicleNumber());
        parkingLotApplication.getParkingLot().getStatus();

        // for a valid parkingTicket, initiate vehicle exit
        parkingLotApplication.getParkingLot().payAndExit(parkingTicket);
        parkingLotApplication.getParkingLot().getStatus();
    }

    // add parking floors in parking lot
    public void init(int totalParkingFloors, int twoWheelerCapacity, int fourWheelerCapacity){
        for(int i=0;i<totalParkingFloors;i++){
            ParkingFloor parkingFloor = new ParkingFloor(i);
            parkingFloor.addParkingSpotForVehicleType(VehicleType.TWO_WHEELER, twoWheelerCapacity);
            parkingFloor.addParkingSpotForVehicleType(VehicleType.FOUR_WHEELER, fourWheelerCapacity);
            getParkingLot().addParkingFloor(parkingFloor);
        }
    }
}
