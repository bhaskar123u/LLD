package designPatternUsecase.OnlineAuction.src;

public interface AuctionMediator {
  
  void addBidder(Team iplTeam);

  void placeBid(Team iplTeam, int bidAmount);
}
