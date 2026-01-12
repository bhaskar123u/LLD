package designPatternUsecase.ParkingLot.src.models;

public class ParkingSpot {
    private final int parkingSpotId;
    private final int parkingFloorId;
    private final VehicleType vehicleType;
    private boolean isAvailable;

    public ParkingSpot(int parkingSpotId, int parkingFloorId, VehicleType vehicleType) {
        this.parkingSpotId = parkingSpotId;
        this.parkingFloorId = parkingFloorId;
        this.vehicleType = vehicleType;
        this.isAvailable = true;
    }

    public int getParkingFloorId(){
        return parkingFloorId;
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    public VehicleType getVehicleType(){
        return vehicleType;
    }

    public void reserveSpot(){
        isAvailable = false;
    }

    public void unReserveSpot(){
        isAvailable = true;
    }
}

/*
Cyclic object references :

Assume I add a field : private ParkingTicket parkingTicket. Also in parking ticket I have a parkingSpot field. But who owns the relationship? What is the optimal design?

Ownership means responsibility for creating/updating/deleting the link. If both objects try to "own" the relation, consistency issues happen. If one owns and the other just references, it’s clean.

A Parking Ticket exists because a spot is assigned. A Parking Spot should not be aware of ticket lifetime beyond occupied/free.

So better ownership is: ParkingTicket owns the ParkingSpot reference, not vice versa.
class ParkingTicket {
    private ParkingSpot spot;
    // other fields;
}
class ParkingSpot {
    private boolean isAvailable;
    // other fields;
}

Use this heuristic: Does object A need to ask for B through object C often?
If yes → bidirectional is justified.
If not → keep it unidirectional.

When printing ticket, you need spot so (Ticket → Spot) is needed.
When freeing a spot, you know the ticket anyway so (Spot → Ticket) isn't essential.
*/
