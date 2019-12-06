package echo;

import java.net.Socket;

public class CacheHandler extends ProxyHandler{
	
	public CacheHandler() {
		super();
	}
	
	public CacheHandler(Socket s) {
		super(s);
	}
	
	protected String response(String msg) {
		String string = Cache.search(msg);
		if (string != null) {
			System.out.println("Found in the cache.");
			return string;
		}
		else {
			peer.writeObject(new Message<String>(msg));
			String message = ((Message<String>) peer.readObject()).toString();
			Cache.update(msg, message);
			return message;
		}
	}
}
