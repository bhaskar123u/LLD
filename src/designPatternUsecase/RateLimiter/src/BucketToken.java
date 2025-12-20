package designPatternUsecase.RateLimiter.src;

public interface BucketToken {
  
  int getToken();

  void resetTokenCounter();
}
