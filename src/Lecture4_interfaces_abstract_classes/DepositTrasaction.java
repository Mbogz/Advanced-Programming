package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class DepositTrasaction extends BaseTransaction {
    public DepositTransaction(double amount, Calendar date, String transactionID) {
        super(amount, date, transactionID);
    }
    private boolean checkDepositAmount(int amt){
        if (amt < 0){
           return false;
        }
        else{
            return  true;
        }
    }

    // Method to print a transaction receipt or details
    public void printTransactionDetails(){
        System.out.println("Deposit Trasaction: "+this.toString());
    }

    @Override
    public void apply(BankAccount ba) {
        ba.deposit(getAmount());
    }
}
