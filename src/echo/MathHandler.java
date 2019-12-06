package echo;

import java.net.Socket;

public class MathHandler extends RequestHandler {
	public MathHandler(Socket s) {
		super (s);
	}

	public MathHandler() {};

	protected String response(String msg) {
		String[] function = msg.split(" ");
		String operand = function[0];
		int answer = 0;
		try {
			if(operand.equals("add")) {
				for(int i = 1; i < function.length; i++) {
					answer += Integer.valueOf(function[i]);
				}
				return String.valueOf(answer);
			}
			else if(operand.equals("mul")) {
				answer = Integer.valueOf(function[1]);
				for(int i = 2; i < function.length; i++) {
					answer *= Integer.valueOf(function[i]);
				}
				return String.valueOf(answer);
			}
			else if(operand.equals("sub")) {
				answer = Integer.valueOf(function[1]);
				for(int i = 2; i < function.length; i++) {
					answer -= Integer.valueOf(function[i]);
				}
				return String.valueOf(answer);
			}
			else if(operand.equals("div")) {
				answer = Integer.valueOf(function[1]);
				for(int i = 2; i < function.length; i++) {
					answer /= Integer.valueOf(function[i]);
				}
				return String.valueOf(answer);
			}
			return msg;
		}
		catch(NumberFormatException e){
			return "Please use numerical values.";
		}
	}
}
