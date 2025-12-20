package designPatterns.visitor.src;


public final class InternationalCardTransaction implements CardTransaction {

    private final double amount;
    private final String country;
    private final boolean chipAndPin;

    public InternationalCardTransaction(double amount, String country, boolean chipAndPin) {
        this.amount = amount;
        this.country = country;
        this.chipAndPin = chipAndPin;
    }

    public double getAmount() {
        return amount;
    }

    public String getCountry() {
        return country;
    }

    public boolean isChipAndPin() {
        return chipAndPin;
    }

    @Override
    public void accept(CardTransactionVisitor visitor) {
        visitor.visit(this);
    }
}
