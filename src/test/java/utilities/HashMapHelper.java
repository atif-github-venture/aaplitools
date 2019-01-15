package utilities;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>This is a class containing all the methods related to operations on Thread Local</h1>
 */

public class HashMapHelper {
	private HashMap<String, Object> sharedData;

	public HashMapHelper(){
		sharedData = new HashMap<>();
	}

	public Map<String, Object> getMap() {
		return sharedData;
	}
}
