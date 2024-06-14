/*
 * getProperties(String key): This method reads the specified property from the configuration file and returns its value.
 * setProperties(String key, String value): This method writes the specified property and its value to the configuration file.
 */

package com.saucedemo.propertiesconfig;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {
	public static Properties properties = new Properties();
	final public static String projectPath = System.getProperty("user.dir");

	public static String getProperties(String key) {
		try {
			InputStream inputStream = new FileInputStream(
					projectPath + "/src/test/java/com/saucedemo/propertiesconfig/config.properties");
			properties.load(inputStream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return properties.getProperty(key);
	}


}
