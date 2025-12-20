package designPatterns.visitor.src;


public final class DomesticCardTransaction implements CardTransaction {

    private final double amount;
    private final String merchantCategory; // e.g. GROCERY, FUEL

    public DomesticCardTransaction(double amount, String merchantCategory) {
        this.amount = amount;
        this.merchantCategory = merchantCategory;
    }

    public double getAmount() {
        return amount;
    }

    public String getMerchantCategory() {
        return merchantCategory;
    }

    @Override
    public void accept(CardTransactionVisitor visitor) {
        visitor.visit(this);
    }
}
