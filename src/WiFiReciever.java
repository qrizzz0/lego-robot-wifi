import lejos.hardware.motor.Motor;
import java.io.*;
import java.net.*;

public class WiFiReciever {

	static MotorController wheelbase = new MotorController(); //Lav non-static, kan ikke huske Java
	
	public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1337);
		System.out.println(" Server started!  " );
        
        boolean kill = false;
        while(!kill) {
        	Socket connectionSocket = serverSocket.accept();
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
        	
            boolean exit = false;
            while (!exit) {
            	socketWriter.write("Indtast kommando pls:\r\n");
            	socketWriter.flush();
            	
            	String input = socketReader.readLine();
            	String inputArgs[] = input.split(" ");
            	
            	
            	switch (inputArgs[0]) {
            	case "drive":
            		if (inputArgs.length > 2) {
            			wheelbase.drive(Integer.parseInt(inputArgs[1]), Integer.parseInt(inputArgs[2]));
            		} else {
            			wheelbase.drive(Integer.parseInt(inputArgs[1]), 500);
            		}
            		break;
            	case "left":
            		wheelbase.turnLeft(Integer.parseInt(inputArgs[1]));
            		break;
            	case "right":
            		wheelbase.turnRight(Integer.parseInt(inputArgs[1]));
            		break;
            	case "stop":
            		Motor.A.stop(true);
            		Motor.B.stop(true);
            		break;
            	case "exit":
            	case "quit":
            		socketWriter.write("Exiting!\r\n");
            		exit = true;
            		break;
            	case "KILL":
            		exit = true;
            		kill = true;
            	default:
            		socketWriter.write("Command not understood!\r\n");
            	}
            }
            connectionSocket.close();
        }
        serverSocket.close();
	}
}
