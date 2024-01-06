import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Account {
    private final String PIN;
    private double balance;
    Account() {
        PIN = "1234";
        loadBalance();
    }
    protected boolean PIN_match(String PIN) {
        return (PIN.equals(this.PIN));
    }
    protected double getBalance() {
        return this.balance;
    }
    protected void deposit(double amount) {
        this.balance += amount;
        updateBalance();
    }
    protected boolean withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            updateBalance();
            return true;
        } else {
            return false;
        }
    }
    private void updateBalance() {
        File balanceINFO = new File("src/FILES/balanceINFO.txt");
        try {
            PrintWriter pw = new PrintWriter(balanceINFO);
            pw.println(this.balance);
            pw.close();
        } catch (Exception e) {
            assert true;
        }
    }
    private void loadBalance() {
        File balanceINFO = new File("src/FILES/balanceINFO.txt");
        try {
            this.balance = (new Scanner(balanceINFO)).nextDouble();
        } catch (Exception e) {
            this.balance = 0.0;
        }
    }
}
