package com.epam.soika;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class RunnerClass
{
    public static void main( String[] args ) throws InterruptedException
    {
        Bank bank = new Bank(10);
        Thread bankThread = new Thread(bank);
        bankThread.start();
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++){
            Transactor t = new Transactor(bank);
            Thread thr = new Thread(t);
            thr.start();
        }

        System.out.println( "Hello World!" );
    }
}
