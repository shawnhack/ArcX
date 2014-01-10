package com.bitmind.util;

import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

	private static final String FILENAME = "config.properties";
	private static final Properties properties = new Properties();

	static {
		try {
			properties.load(PropertyManager.class.getClassLoader()
					.getResourceAsStream(FILENAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final String PROXY_HOST = findStringAttribute("proxy.host");
	public static final String PROXY_PORT = findStringAttribute("proxy.port");

	/**
	 * Gets a String attribute from the properties file.
	 * 
	 * @param key
	 *            The attribute name.
	 * @return The value.
	 */
	private static String findStringAttribute(String key) {
		String value = "";
		value = properties.getProperty(key);
		return value != null ? value.trim() : "";
	}
}
