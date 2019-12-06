package echo;

import java.net.Socket;

public class RequestHandler extends Correspondent implements Runnable {
	public RequestHandler(Socket s) { super(s); }
	public RequestHandler() { }
	// override in a subclass:
	protected String response(String msg) {
		return "echo: " + msg;
	}
	
	public void run() {
		while(true) {
			// receive request
			Message<String> s = (Message<String>)(this.readObject());
			String string = s.toString();
			System.out.println("received: " + string);
			// send response	
			if(string.equals("quit")) {
				System.out.println("Shutting down RequestHandler.");
				break;
			}
			if(string.equals("server quit")) {
				System.out.println("Server quitting.");
				this.close();
			}
			else {
				String response = response(string);
				System.out.println("sending: " + response);
				writeObject(new Message<String>(response));
			}
		}
	}
}
