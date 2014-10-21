package com.epam.memoryManage;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;

/**
 * Created by andrei_soika on 10/20/2014.
 */
public class MainApp {

    public final static Logger logger = Logger.getLogger(MainApp.class);
    private static long count = 0;

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {

        //Test pass by reference
        Point3D first = new Point3D(1,2,3);
        Point3D second = new Point3D(4,5,6);
        logger.info("Send var by reference");
        logger.info("First before" + first);
        logger.info("Second before" + second);
        methodByRef(first, second);
        logger.info("First after" + first);
        logger.info("Second after" + second);

        //Test pass by value and reference
        logger.info("Pass var by value... or reference");
        long number = 444L;
        methodByValueOrRef(number);
        logger.info("After by value " + number);
        Long nnumber = new Long(444);//444L;
        methodByValueOrRef(nnumber);
        logger.info("After by reference " + nnumber);

        //Test pass by value
        long numberLong = 444L;
        int numberInt = 777;
        methodByValue(numberLong, numberInt);
        logger.info("Long - " + numberLong + " Int - " + numberInt);

        long[] arr = new long[2];
        for (int i = 0; i < 2; i++) {
            arr[i] = i;
        }
        try {
            methodByRef(arr);
        } catch (StackOverflowError e) {
            //e.printStackTrace();
            logger.info("Count " + count);
        }


    }

    public static void methodByRef(Point3D pointOne, Point3D pointTwo){
        pointOne = new Point3D(5,5,5);
        pointTwo.setX(7);
        pointTwo.setZ(7);
    }

    public static void methodByRef(long[] array){
       //long[] f = new long[1000000];
        array[0] = 0L;
        count++;
        methodByRef(array);
    }

    public static void methodByValueOrRef(Long number) throws IllegalAccessException, NoSuchFieldException {
        Field f = number.getClass().getDeclaredField("value");
        f.setAccessible(true);
        f.setLong(number,5L);
        logger.info(number);
    }

    public static void methodByValue(long numberLong, int numberInt){
        numberLong += 5;
        numberInt = -111;
    }
}
