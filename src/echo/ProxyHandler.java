package echo;

import java.net.Socket;

public class ProxyHandler extends RequestHandler {

	protected Correspondent peer;

	public ProxyHandler(Socket s) { super(s); }
	public ProxyHandler() { super(); }

	public void initPeer(String host, int port) {
		peer = new Correspondent();
		peer.requestConnection(host, port);
	}

	protected String response(String msg) {
		peer.writeObject(new Message<String>(msg));
		Message<String> m = (Message<String>)peer.readObject();
		return m.toString();
	}
}
