package designPatterns.visitor.src;


import java.util.HashMap;
import java.util.Map;

public class RiskAssessmentVisitor implements CardTransactionVisitor {

    Map<CardTransaction, String> riskMap;

    RiskAssessmentVisitor() {
        riskMap = new HashMap<>();
    }

    public Map<CardTransaction, String> getRiskResults() {
        return riskMap;
    }

    public void visit(DomesticCardTransaction tx) {
        if(tx.getMerchantCategory().equals(MerchantCategory.FUEL.toString()))
            riskMap.put(tx, Risk.LOW.toString());
        else if(tx.getAmount() > 50000)
            riskMap.put(tx, Risk.MEDIUM.toString());
    }

    public void visit(InternationalCardTransaction tx) {
        if(!tx.isChipAndPin())
            riskMap.put(tx, Risk.HIGH.toString());
        if(Utils.getFlaggedCountry().contains(tx.getCountry()))
            riskMap.put(tx, Risk.HIGH.toString());
    }

    public void visit(EMITransaction tx) {
        if(tx.getTenureMonths() > 12)
            riskMap.put(tx, Risk.MEDIUM.toString());
        else {
            riskMap.put(tx, Risk.LOW.toString());
        }
    }
}
