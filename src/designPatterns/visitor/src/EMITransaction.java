package designPatterns.visitor.src;


public final class EMITransaction implements CardTransaction {

    private final double totalAmount;
    private final int tenureMonths;

    public EMITransaction(double totalAmount, int tenureMonths) {
        this.totalAmount = totalAmount;
        this.tenureMonths = tenureMonths;
    }

    public double getAmount() {
        return totalAmount;
    }

    public int getTenureMonths() {
        return tenureMonths;
    }

    @Override
    public void accept(CardTransactionVisitor visitor) {
        visitor.visit(this);
    }
}
