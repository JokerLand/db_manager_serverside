package connection;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public class OutputThread extends Thread {

	private Socket socket;
	private BufferedWriter bufferedWriter;
	
	public OutputThread(Socket socket, BufferedWriter bufferedWriter) {
		this.socket = socket;
		this.bufferedWriter = bufferedWriter;
	}
	
	@Override
	public void run() {
		boolean running = true;
		while(running) {
			try {
				if(!socket.isClosed()) {
					String line = "Message from server to client.";
					synchronized(bufferedWriter) {
						bufferedWriter.write(line + "\r\n");
						bufferedWriter.flush();
					}
					Thread.sleep(3000);
				}
			} catch (IOException e) {
				System.out.println("Impossible to write/flush (BufferedWriter)");
				e.printStackTrace();
			} catch (InterruptedException e) {
				System.out.println("Impossible to interrupt OutputThread");
				e.printStackTrace();
			}
		}
	}
	
}
