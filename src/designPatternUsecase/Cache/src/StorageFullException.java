package designPatternUsecase.Cache.src;

public class StorageFullException extends RuntimeException {
  public StorageFullException(String message) {
    super(message);
  }
}
