package designPatternUsecase.ParkingLot.src.models;

import designPatternUsecase.ParkingLot.src.controllers.ParkingTicketGenerator;
import designPatternUsecase.ParkingLot.src.strategies.HourlyStrategy;
import designPatternUsecase.ParkingLot.src.strategies.PaymentStrategy;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private String parkingLotName;
    private List<ParkingFloor> parkingFloorList;
    private ParkingTicketGenerator parkingTicketGenerator;
    private PaymentStrategy paymentStrategy;

    public ParkingLot(String parkingLotName){
        this.parkingLotName = parkingLotName;
        parkingFloorList = new ArrayList<>();
        this.parkingTicketGenerator = new ParkingTicketGenerator();
        this.paymentStrategy = new HourlyStrategy();
    }

    public void addParkingFloor(ParkingFloor parkingFloor){
        parkingFloorList.add(parkingFloor);
    }

    public void removeParkingFloor(int parkingFloorId){
        this.parkingFloorList = parkingFloorList.stream().filter(parkingFloor->{
            return !(parkingFloor.getParkingFloorId() == parkingFloorId);
        }).toList();
    }

    public void changePaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    public synchronized ParkingTicket getParkingTicket(Vehicle vehicle) {
        ParkingSpot spot = getAvailableParkingSpot(vehicle.vehicleType());
        if (spot == null) {
            System.out.println("No spot available for vehicle type: " + vehicle.vehicleType());
            return null;
        }
        return parkingTicketGenerator.generateParkingTicket(vehicle, spot);
    }

    public synchronized ParkingSpot getAvailableParkingSpot(VehicleType type) {
        for (ParkingFloor floor : parkingFloorList) {
            ParkingSpot spot = floor.allocateSpot(type);
            if (spot != null) {
                return spot;
            }
        }
        return null;
    }

    public synchronized void payAndExit(ParkingTicket ticket) {
        ticket.setTicketEndTime(LocalTime.now());

        ParkingSpot spot = ticket.getParkingSpot();
        int floorId = spot.getParkingFloorId();
        ParkingFloor floor = parkingFloorList.get(floorId);
        floor.releaseSpot(spot);

        System.out.println("[Exiting] : Thank you " + ticket.getVehicle().vehicleNumber() + ", Please visit again");
    }

    public void getStatus(){
        // this returns floor wise count of occupied vehicles
        System.out.println("******************** Floor wise vehicle occupancy report for "+parkingLotName+" ********************");
        for(int i=0;i<parkingFloorList.size();i++){
            System.out.println("Floor "+ (i+1) + " : " +parkingFloorList.get(i).getStatus());
        }
        System.out.println("***************************************** End of report *****************************************");
    }
}
