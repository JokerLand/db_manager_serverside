package connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class WaitingConnection extends Thread {

	private ServerSocket serverSocket;
	private Vector<Socket> socketsList;
	
	public WaitingConnection(ServerSocket serverSocket, Vector<Socket> socketsList) {
		this.serverSocket = serverSocket;
		this.socketsList = socketsList;
	}
	
	@Override
	public void run() {
		boolean running = true;
		while(running) {
			try {
				if(!serverSocket.isClosed()) {
					Socket socket = serverSocket.accept();
					socketsList.addElement(socket);
					System.out.println("Client connected to server. [Total : " + socketsList.size() + "]");
					InputStreamReader input = new InputStreamReader(socket.getInputStream());
					OutputStreamWriter output = new OutputStreamWriter(socket.getOutputStream());
					BufferedReader in = new BufferedReader(input);
					BufferedWriter out = new BufferedWriter(output);
					
					InputThread inputThread = new InputThread(socket, in);
					inputThread.start();
					OutputThread outputThread = new OutputThread(socket, out);
					outputThread.start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
