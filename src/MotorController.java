import lejos.hardware.motor.Motor;

public class MotorController {

	public void drive(int length, int speed) {
		Motor.A.setSpeed(speed);
		Motor.B.setSpeed(speed);
		
		length = length * 10; //Udregn cm->grader på et tidspunkt
		
		Motor.A.rotate(length, true);
		Motor.B.rotate(length, true);
	}
	
	public void turnLeft(int degrees) {
		Motor.A.setSpeed(500);
		Motor.B.setSpeed(500);
		Motor.A.rotate(degrees, true);	
		Motor.B.rotate(-degrees, true);	
	}
	
	public void turnRight(int degrees) {
		Motor.A.setSpeed(500);
		Motor.B.setSpeed(500);
		Motor.A.rotate(-degrees, true);	
		Motor.B.rotate(degrees, true);	
	}	
	
	
}
