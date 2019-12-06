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
		send(new String(msg));
		String m = (String)receive();
		return m.toString();
	}
}
