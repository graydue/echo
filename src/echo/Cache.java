package echo;

import java.util.HashMap;

public class Cache extends HashMap<String, String>{
	
	private static Cache cache = new Cache();
	
	public static String search(String request) {
		return cache.get(request);
	}
	
	public synchronized static void update(String request, String response) {
		cache.put(request, response);
	}
}
