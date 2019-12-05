package echo;

import java.net.Socket;

public class MathHandler extends RequestHandler {
	public MathHandler(Socket s) {
		super (s);
	}

	public MathHandler() {};

	protected String response(String msg) {
		String[] function = msg.split(" ");
		int answer = 0;
		String operand = function[0];
		try {
			if(operand.equals("add")) {
				answer = Integer.valueOf(function[1]) + Integer.valueOf(function[2]);
			}
			else if(operand.equals("mul")) {
				answer = Integer.valueOf(function[1]) * Integer.valueOf(function[2]);
			}
			else if(operand.equals("sub")) {
				answer = Integer.valueOf(function[1]) - Integer.valueOf(function[2]);
			}
			else if(operand.equals("div")) {
				answer = Integer.valueOf(function[1]) / Integer.valueOf(function[2]);
			}
			return String.valueOf(answer);
		}
		catch(NumberFormatException e){
			return "Please use numerical values only.";
		}
	}

	public void run() {
		while(true) {
			// receive request
			String s = this.receive();
			// send response
			this.send(this.response(s));
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
