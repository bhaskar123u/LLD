package designPatterns.visitor.src;

public interface CardTransaction {
    void accept(CardTransactionVisitor visitor);
    double getAmount();
}
