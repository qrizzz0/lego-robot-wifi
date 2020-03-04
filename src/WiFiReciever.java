import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.Image;
import lejos.hardware.motor.Motor;
import java.io.*;
import java.net.*;

import javax.imageio.ImageIO;

public class WiFiReciever {

	
	public static void main(String[] args) throws Exception {
		//Init hardware
		MotorControl motors = new MotorControl(); //Lav non-static, kan ikke huske Java
		motors.initMotors();
		//Sensors sensors = new Sensors();
		//sensors.initSensors();
		
		ServerSocket serverSocket = new ServerSocket(1337);
		System.out.println("Server running!");
		GraphicsLCD gLCD = BrickFinder.getDefault().getGraphicsLCD();
		//final Image pinkie = ImageIO.read(new File("/home/lejos"));
		//gLCD.drawImage(src, 20, 20);
        
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
            			motors.drive(Integer.parseInt(inputArgs[1]), Integer.parseInt(inputArgs[2]));
            		} else {
            			motors.drive(Integer.parseInt(inputArgs[1]), 500);
            		}
            		break;
            	
            	case "left":
            		motors.turnLeft(Integer.parseInt(inputArgs[1]));
            		break;
            	
            	case "right":
            		motors.turnRight(Integer.parseInt(inputArgs[1]));
            		break;
            	
            	case "stop":
            		motors.stopDriving();
            		break;
            		
            	case "arm":
            		if (inputArgs.length > 2) {
            			motors.arm(Integer.parseInt(inputArgs[1]), Integer.parseInt(inputArgs[2]));
            		} else {
            			motors.arm(Integer.parseInt(inputArgs[1]), 300);
            		}
            		break;
            		
            	case "stoparm":
            		motors.stopArm();
            		
            	case "irsensor":
            		//socketWriter.write(String.valueOf(sensors.readIRSensor()));
            		break;
            		
            	case "ussensor":
            		//socketWriter.write(String.valueOf(sensors.readUSSensor()));
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
