package echo;

import java.util.*;
import java.io.*;
import java.net.*;

public class Server {

	protected ServerSocket mySocket;
	protected int myPort;
	public static boolean DEBUG = true;
	protected Class<?> handlerType;

	public Server(int port, String handlerType) {
		try {
			myPort = port;
			mySocket = new ServerSocket(myPort);
			this.handlerType = (Class.forName(handlerType));
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} // catch
	}


	public void listen() {
		while(true) {
			// accept a connection
			try {
				Socket s = mySocket.accept();
				Thread t = new Thread(makeHandler(s));
				t.start();
				System.out.println("Server listening at port " + myPort);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// make handler
			// start handler
		} // while
	}

	public RequestHandler makeHandler(Socket s) {
		RequestHandler handler = new RequestHandler(s);
		try {
			handler = (RequestHandler) handlerType.newInstance();
			handler.setSocket(s);
		}
		catch (InstantiationException | IllegalAccessException e){
			e.printStackTrace();
		}
		return handler;
		// handler = a new instance of handlerType
		// set handler's socket to s
		// return handler
	}



	public static void main(String[] args) {
		int port = 5555;
		String service = "echo.RequestHandler";
		if (1 <= args.length) {
			service = args[0];
		}
		if (2 <= args.length) {
			port = Integer.parseInt(args[1]);
		}
		Server server = new Server(port, service);
		server.listen();
	}
}