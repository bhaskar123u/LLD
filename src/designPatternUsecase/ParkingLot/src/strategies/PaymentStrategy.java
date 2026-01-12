package designPatternUsecase.ParkingLot.src.strategies;

import designPatternUsecase.ParkingLot.src.models.ParkingTicket;

public interface PaymentStrategy {
    public double getFinalPrice(ParkingTicket parkingTicket);
}
