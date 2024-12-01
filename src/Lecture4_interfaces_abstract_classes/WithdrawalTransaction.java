public class WithdrawalTransaction extends BaseTransaction {
    private double unmetAmount;

    public WithdrawalTransaction(double amount, java.util.Calendar date, String transactionID) {
        super(amount, date, transactionID);
        this.unmetAmount = 0.0;
    }

    @Override
    public void apply(BankAccount ba) throws InsufficientFundsException {
        super.apply(ba);  // Call the base class apply method
        if (ba.getBalance() < getAmount()) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal. Available balance: " + ba.getBalance());
        }
        ba.withdraw(getAmount());
        System.out.println("Withdrawal of " + getAmount() + " applied to account.");
    }

    // Overloaded apply method that supports partial withdrawal
    public void apply(BankAccount ba, boolean allowPartial) throws InsufficientFundsException {
        super.apply(ba);  // Call to the base class apply method
        if (allowPartial) {
            if (ba.getBalance() < getAmount()) {
                unmetAmount = getAmount() - ba.getBalance();
                ba.withdraw(ba.getBalance());  // Withdraw all available funds
                System.out.println("Partial withdrawal applied. Unmet amount: " + unmetAmount);
            } else {
                ba.withdraw(getAmount());  // Full withdrawal
                System.out.println("Full withdrawal of " + getAmount() + " applied.");
            }
        } else {
            // No partial withdrawal allowed, proceed with full withdrawal check
            if (ba.getBalance() < getAmount()) {
                throw new InsufficientFundsException("Insufficient funds for withdrawal. Available balance: " + ba.getBalance());
            }
            ba.withdraw(getAmount());
            System.out.println("Full withdrawal of " + getAmount() + " applied.");
        }
        System.out.println("Transaction complete. Current balance: " + ba.getBalance());
    }

    @Override
    public boolean reverse() {
        if (associatedAccount == null) {
            System.out.println("Transaction not associated with an account. Cannot reverse.");
            return false;
        }
        associatedAccount.deposit(getAmount());
        System.out.println("Withdrawal of " + getAmount() + " reversed.");
        return true;
    }

    // Getter method for unmetAmount
    public double getUnmetAmount() {
        return unmetAmount;
    }
}
