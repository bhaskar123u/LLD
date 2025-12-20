package designPatterns.visitor.src;


public interface CardTransactionVisitor {

    default void visit(DomesticCardTransaction tx) {}

    default void visit(InternationalCardTransaction tx) {}

    default void visit(EMITransaction tx) {}
}
