package echo;

import java.net.Socket;

public class CacheServer extends ProxyServer {
	public CacheServer (int myPort, String service, int peerPort, String peerHost) {
		super(myPort, service, peerPort, peerHost);
	}
	
	public RequestHandler makeHandler(Socket s) {
		RequestHandler handler = super.makeHandler(s);
		((CacheHandler)handler).initPeer(peerHost, peerPort);
		return handler;
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		int port = 5555;
		int peerPort = 6666;
		String peerHost = "localHost";
		String service = "echo.ProxyHandler";
		
		if (1 <= args.length) {
			service = args[0];
		}
		if (2 <= args.length) {
			peerPort = Integer.parseInt(args[1]);
		}
		if (3 <= args.length) {
			port = Integer.parseInt(args[2]);
		}
		if (4 <= args.length) {
			peerHost = args[3];
		}
		Server server = new CacheServer(port, service, peerPort, peerHost);
		server.listen();
	}
}
