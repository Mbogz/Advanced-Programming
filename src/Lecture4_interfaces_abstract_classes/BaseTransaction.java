package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public abstract class BaseTransaction implements TransactionInterface {
    private double amount;
    private Calendar date;
    private String transactionID;

    /**
     * Lecture1_adt.TransactionInterface Constructor
     * @param amount in an integer
     * @param date: Not null, and must be a Calendar object
     * @return void
     * Instialises the field, attributes of a transaction
     * Creates a object of this
     */
    public BaseTransaction(double amount, Calendar date, String transactionID) {
        this.amount = amount;
        this.date = date;
        this.transactionID = transactionID;
    }

    /**
     * getAmount()
     * Method to get the transaction amount
     * @return double
     */
    @Override
    public double getAmount() {
        return amount;
    }

    /**
     * getDate()
     *  Method to get the transaction date
     * @return Calendar Object
     */
    @Override
    public Calendar getDate() {
//        return date;    // Because we are dealing with Reference types we need to judiciously copy what our getters return
        //return (Calendar) date.clone(); // Defensive copying or Judicious Copying
        return date;
    }

    // Method to get a unique identifier for the transaction
    @Override
    public String getTransactionID(){
        return  transactionID;
    }
    // Method to print a transaction receipt or details
    @Override
    public void printTransactionDetails() {
        System.out.println("transaction id: " + transactionID);
        System.out.println("transaction date: " + date.getTime());
        System.out.println("transaction amount: " + amount);
    }

    // A method that applies this transaction on a Bank account object passed as a parameter
    public void apply(BankAccount ba) throws InsufficientFundsException {
        ba.deposit(amount);
    }
}