package designPatterns.visitor.src;

import java.util.List;

public class Visitor {
    public static void main(String[] args) {
        List<CardTransaction> txs = List.of(
            new DomesticCardTransaction(60000, "GROCERY"),
            new InternationalCardTransaction(20000, "PAK", false),
            new EMITransaction(100000, 18)
        );

        RiskAssessmentVisitor visitor = new RiskAssessmentVisitor();
        for (CardTransaction tx : txs) {
            tx.accept(visitor);
        }

        visitor.getRiskResults().forEach((tx, risk) ->
            System.out.println(tx.getClass().getSimpleName() + " -> " + risk)
        );

    }
}