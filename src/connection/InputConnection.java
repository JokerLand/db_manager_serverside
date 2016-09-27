package connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class InputConnection {

	static final int SERVER_PORT = 5000;
	
	ServerSocket serverSocket;
	
	Vector<Socket> socketsList;
	
	public InputConnection() {
		
	}
	
	public void init() {
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Server socket instantiated");
			socketsList = new Vector<Socket>();
			WaitingConnection waitCon = new WaitingConnection(serverSocket, socketsList);
			waitCon.start();
		} catch (IOException e) {
			System.out.println("Impossible to instantiate server socket on port : " + SERVER_PORT);
			e.printStackTrace();
		}
	}
	
}
