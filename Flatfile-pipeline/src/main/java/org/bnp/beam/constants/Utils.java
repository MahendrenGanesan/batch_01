package org.bnp.beam.constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder; 

public final class Utils {

	//private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());

	private Utils() {
	}

	public static Properties extractProperties() {
		Properties prop = new Properties();
		ClassLoader classLoader = Utils.class.getClassLoader();
		InputStream input = null;
		try {
			//File file = new File(classLoader.getResource("conf/config.properties").getFile());	
			input = classLoader.getResourceAsStream("/data/home/mahendren/workspace-spring/Flatfile-pipeline/src/main/resource/config.properties");
		//	input = new FileInputStream(file);
			if (input != null) {
			prop.load(input);
			}
		} catch (IOException ex) {
			//LOGGER.info("Exception " + ex.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ex) {
				//	LOGGER.info("Exception " + ex.getMessage());
				}
			}

		}
		return prop;
	}

	
	public static JSONObject getJsonObject(String value) throws Exception {
		JSONParser parser = new JSONParser();
		try {
			if(null != value && ! "".equals(value)){
				Object obj = parser.parse(value);
				return (JSONObject) obj;
			}
		} catch(Exception ex){
			throw new Exception(ex);
		}	
		return null;
	}
	
	public static String getJsonString(Object value) {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(value);
	}
	

}

