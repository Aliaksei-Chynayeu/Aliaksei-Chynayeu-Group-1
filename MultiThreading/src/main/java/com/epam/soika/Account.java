package com.epam.soika;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrei_Soika on 10/28/2014.
 */
public class Account {
    private Lock lock;
    private int id;
    private long amount;

    public Account(int id) {
        this();
        this.id = id;
    }

    public Account() {
        this.amount = (new Random()).nextInt(100);
        this.lock = new ReentrantLock();
    }

    public int getId() {
        return id;
    }

    public Lock getLock() {
        return lock;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Account(long amount) {

        this.amount = amount;
    }

    public long getAmount() {

        return amount;
    }

    public void withdraw(int amount) throws IllegalAccessError {
        if (this.amount >= amount) {
            this.amount -= amount;
        } else {
            throw new IllegalAccessError("Not enough amount");
        }
    }

    public void deposit(int amount) {
        this.amount += amount;
    }

    @Override
    public String toString() {
        return id + ">" + amount + " ";
    }
}
