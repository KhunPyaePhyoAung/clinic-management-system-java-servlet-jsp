package me.khun.clinic.util;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	private Properties properties;
	
	public PropertyReader(String location) {
		this.properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream(location));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getValue(String key) {
		return properties.getProperty(key);
	}
}
