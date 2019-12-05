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
			String s = this.receive();
			// send response	
			this.send(response(s));
			// sleep
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// close
	}
}
