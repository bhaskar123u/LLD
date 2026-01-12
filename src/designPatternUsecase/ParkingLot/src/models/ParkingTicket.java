package designPatternUsecase.ParkingLot.src.models;

import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class ParkingTicket {
    private final String parkingTicketId;
    private final LocalTime ticketStartTime;
    private LocalTime ticketEndTime;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;

    public ParkingTicket(Vehicle vehicle, ParkingSpot parkingSpot){
        this.parkingTicketId = UUID.randomUUID().toString();
        this.ticketStartTime = LocalTime.now();
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
    }

    public String getParkingTicketId() {
        return parkingTicketId;
    }

    public LocalTime getTicketStartTime() {
        return ticketStartTime;
    }

    public LocalTime getTicketEndTime() {
        return ticketEndTime;
    }

    public void setTicketEndTime(LocalTime ticketEndTime) {
        this.ticketEndTime = ticketEndTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof ParkingTicket that)) return false;

        return Objects.equals(parkingTicketId, that.parkingTicketId) && ticketStartTime.equals(that.ticketStartTime) && ticketEndTime.equals(that.ticketEndTime) && vehicle.equals(that.vehicle) && parkingSpot.equals(that.parkingSpot);
    }

    @Override
    public int hashCode() {
        int result = getParkingTicketId().hashCode();
        result = 31 * result + getTicketStartTime().hashCode();
        result = 31 * result + getTicketEndTime().hashCode();
        result = 31 * result + getVehicle().hashCode();
        result = 31 * result + getParkingSpot().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "parkingTicketId='" + parkingTicketId + '\'' +
                ", ticketStartTime=" + ticketStartTime +
                ", ticketEndTime=" + ticketEndTime +
                ", vehicle=" + vehicle +
                ", parkingSpot=" + parkingSpot +
                '}';
    }
}
