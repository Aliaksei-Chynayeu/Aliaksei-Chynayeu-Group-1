package com.epam.soika;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Test {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		CustomClassLoader cl = null;
		String jarsFolder = "jars";
		List<String> jars = getListJars(jarsFolder);
		int i = 1; 
		System.out.println("Select jar file:" + File.separator);
		for (String s:jars){
			System.out.println(i++ + ": " + s);
		}
		Scanner keyboard = new Scanner(System.in);
		int myint = keyboard.nextInt();
		
		List<String> classNames = getListOfClassFromJar(jarsFolder, jars.get(myint - 1));
		i = 1;
		for (String s:classNames)
		System.out.println(i++ + ": " + s);
		int myintt = keyboard.nextInt();

		
		if (cl == null) cl = new CustomClassLoader();
		cl.setJarName(jarsFolder + File.separator + jars.get(myint - 1));
		Class<?> clas = cl.loadClass(classNames.get(myintt-1));
		Object ob = clas.newInstance();
		Method[] methods = clas.getDeclaredMethods();
		i = 1;
		for (Method method : methods) {
			System.out.println(i++ + ": " + method.getName());
		}
		int methodInt = keyboard.nextInt();
		clas.getMethod(methods[methodInt-1].getName()).invoke(ob);
		
		
	}

	public static List<String> getListOfClassFromJar(String path, String name) throws IOException{
		List<String> classNames = new ArrayList<String>();
		ZipInputStream zip = new ZipInputStream(new FileInputStream(path + File.separator + name));
		for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry())
			if (entry.getName().endsWith(".class") && !entry.isDirectory()) {
				// This ZipEntry represents a class. Now, what class does it
				// represent?
				StringBuilder className = new StringBuilder();
				for (String part : entry.getName().split("/")) {
					if (className.length() != 0)
						className.append(".");
					className.append(part);
					if (part.endsWith(".class"))
						className.setLength(className.length() - ".class".length());
				}
				classNames.add(className.toString());
			}
		return classNames;
	}
	public static List<String> getListJars(String jarsFolder) {
		File folder = new File(jarsFolder);
		File[] listOfFiles = folder.listFiles();
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				//System.out.println("File " + listOfFiles[i].getName());
				result.add(listOfFiles[i].getName());
			}
		}
		return result;

	}
}
