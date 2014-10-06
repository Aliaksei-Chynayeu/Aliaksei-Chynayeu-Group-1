package com.epam.soika;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

public class CustomClassLoader extends ClassLoader {

	//static final Logger logger = Logger.getLogger(CustomClassLoader.class);
	private String jarName = null;
	//private Map classesHash = new HashMap();

	
	
	public CustomClassLoader(ClassLoader parent) {
		super(parent);
	}

	public String getJarName() {
		return jarName;
	}

	public void setJarName(String jarName) {
		this.jarName = jarName;
	}

	public CustomClassLoader() {
		this(CustomClassLoader.class.getClassLoader());
	}

	@Override
	protected Class<?> loadClass(String name, boolean arg1)
			throws ClassNotFoundException {
		synchronized (getClassLoadingLock(name)) {
			Class c = findLoadedClass(name);
			if (c == null) {
				long t0 = System.nanoTime();
				ClassLoader parent = getParent();
				try {
					if (parent != null) {
						c = parent.loadClass(name);
					}
				} catch (ClassNotFoundException e) {

				}
				if (c == null) {
					long t1 = System.nanoTime();
					c = findClass(name);
				}
			}
			if (c == null) {
				throw new ClassNotFoundException();
			}
			return c;
		}
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte classByte[];
		Class result = findLoadedClass(name);
		if (result != null) {
			//logger.info("% Class " + name + " found in cache");
			return result;
		}
		try {
			result = findSystemClass(name);
			if (result == null)
				throw new ClassNotFoundException();
			return result;

		} catch (ClassNotFoundException e) {
			try {
				JarFile jar = new JarFile(jarName);
				JarEntry entry = jar.getJarEntry(name.replace('.', '/')
						+ ".class");
				if (entry == null) {
					throw new ClassNotFoundException(name);
				}
				InputStream is = jar.getInputStream(entry);
				ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
				int nextValue = is.read();
				while (-1 != nextValue) {
					byteStream.write(nextValue);
					nextValue = is.read();
				}
				classByte = byteStream.toByteArray();
				result = defineClass(name, classByte, 0, classByte.length);
				return result;
			} catch (IOException exception) {
				//logger.error("Class not found");
				throw new ClassNotFoundException(name, exception);
			}
		}
	}


}
