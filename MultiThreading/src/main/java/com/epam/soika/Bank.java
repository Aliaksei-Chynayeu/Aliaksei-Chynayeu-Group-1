package com.epam.soika;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Andrei_Soika on 10/28/2014.
 */
public class Bank implements Runnable {
    private static final Logger logger = Logger.getLogger(Bank.class);
    private long amount;
    private boolean closed;
    private volatile List<Account> accounts;
    private AtomicInteger transactorsNumber = new AtomicInteger();

    public Bank(int numberOfAccounts) {
        //this.amount = amount;
        List<Account> accounts = new ArrayList<Account>();
        for (int i = 0; i < numberOfAccounts; i++) {
            accounts.add(new Account(i));
        }
        this.accounts = accounts;
    }

    /*public Bank(int numberOfAccounts) {
        super();
    }*/

    public synchronized void enterBank(){
        transactorsNumber.incrementAndGet();
    }

    public synchronized void exitBank(){
        transactorsNumber.decrementAndGet();
    }

    private boolean isBankEmpty() {
        return transactorsNumber.compareAndSet(0,0);
    }

    public boolean isClosed() {
        return closed;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(5000);
                closed = true;
                isBankEmpty();
                long sum = 0;
                synchronized (accounts) {
                    logger.info("Start summing");
                    StringBuilder sb = new StringBuilder();
                    for (Account ac: accounts) {
                        sum += ac.getAmount();
                        sb.append(ac.toString());
                        Thread.sleep(10);
                    }
                    logger.info(sb.toString());
                    logger.info("End summing");
                    logger.info("Sum is " + sum);
                }
                closed = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getAccountsCount() {
        if (accounts != null) {
            return accounts.size();
        } else {
            return 0;
        }
    }

    public Account getAccountByIndex(int index) {
        if (accounts == null || index < 0 || index > (accounts.size() - 1)) {
            return null;
        } else {
            return accounts.get(index);
        }
    }

}
