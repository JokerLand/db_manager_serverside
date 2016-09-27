package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class InputThread extends Thread {

	private Socket socket;
	private BufferedReader bufferedReader;
	
	public InputThread(Socket socket, BufferedReader bufferedReader) {
		this.socket = socket;
		this.bufferedReader = bufferedReader;
	}
	
	@Override
	public void run() {
		boolean running = true;
		while(running) {
			try {
				if(!socket.isClosed()) {
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						System.out.println("Server input string : " + line);
					}
					if(line == null) {
						running = false;
						socket.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
