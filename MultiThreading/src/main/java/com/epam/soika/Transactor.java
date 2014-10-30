package com.epam.soika;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;

/**
 * Created by Andrei_Soika on 10/29/2014.
 */
public class Transactor implements Runnable {
    private static final Logger logger = Logger.getLogger(Transactor.class);
    private Bank bank = null;
    private static Transactor instance = null;
    private Random r = new Random();

    public Transactor() {
        super();
    }

    public Transactor(Bank bank) {
        super();
        this.bank = bank;
    }

    @Override
    public void run() {
        logger.info("Start");
        Account first, second;
        int numberOfAccount = bank.getAccountsCount();
        while (true) {
            try {
                Thread.sleep(10);
                while (bank.isClosed()) {
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bank.enterBank();
            try {
                int intFirst = getRandomNumber(numberOfAccount);
                int intSecond = getRandomNumber(numberOfAccount, intFirst);
                logger.info("Start transfer: " + intFirst + " > " + intSecond);
                first = bank.getAccountByIndex(intFirst);
                second = bank.getAccountByIndex(intSecond);
                try {
                    transerMoney(first, second, 3);
                } catch (IllegalAccessError e) {
                    logger.error(e.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    logger.info("End transfer: " + intFirst + " > " + intSecond);
                }
            } finally {
                bank.exitBank();
            }
        }
        //logger.info("End");
    }

    private int getRandomNumber(int max, int base) {
        int result = base;
        while (result == base) {
            result = r.nextInt(max);
        }
        return result;
    }

    private int getRandomNumber(int max) {
        return getRandomNumber(max, -1);
    }

    private void transerMoney(Account one, Account two, int amount) throws IllegalAccessError, InterruptedException {
        Account first, second;
        if (one.getId() > two.getId()) {
            first = two;
            second = one;
        } else {
            first = one;
            second = two;
        }
        synchronized (first) {
            sleepT();
            synchronized (second) {
                sleepT();
                /*logger.info(first.getId()+"-"+first.getAmount());
                logger.info(second.getId()+"-"+second.getAmount());*/
                one.withdraw(amount);
                two.deposit(amount);
                /*logger.info(first.getId()+"-"+first.getAmount());
                logger.info(second.getId()+"-"+second.getAmount());*/
            }
        }
    }

    private static void sleepT() {
        for (int i = 0; i < 100000; i++) {
            if (Math.random() * i > 200) ;
        }
    }
}
