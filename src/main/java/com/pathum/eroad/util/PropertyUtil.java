package com.pathum.eroad.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

	static Properties prop = new Properties();

	static {
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getProperty(String name) {
		return prop.getProperty(name);
	}

}
