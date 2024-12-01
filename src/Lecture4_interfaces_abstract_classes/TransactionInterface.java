public interface TransactionInterface {
    double getAmount();
    java.util.Calendar getDate();
    String getTransactionID();
    void printTransactionDetails();
    void apply(BankAccount ba) throws InsufficientFundsException;
    boolean reverse();
}



