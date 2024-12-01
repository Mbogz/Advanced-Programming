public class DepositTransaction extends BaseTransaction {

    public DepositTransaction(double amount, java.util.Calendar date, String transactionID) {
        super(amount, date, transactionID);
    }

    @Override
    public void apply(BankAccount ba) {
        try {
            super.apply(ba);  // Call the apply method of BaseTransaction (if it does anything)

            // Since deposits don't generally have insufficient funds, we just do the deposit
            ba.deposit(getAmount());
            System.out.println("Deposit of " + getAmount() + " applied to account.");
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());  // Catch the exception if it is thrown
        }
    }
}