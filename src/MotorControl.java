import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;

public class MotorControl {

	//Det hele er statics fordi vi kun er interesseret i 1 instans af hver type motor alligevel
	//- vi holder ingen dubletter af variable her.
	
	EV3LargeRegulatedMotor MotorA;
	EV3LargeRegulatedMotor MotorB;
	EV3MediumRegulatedMotor MotorC;
	
	public void initMotors() {
		MotorA = new EV3LargeRegulatedMotor(MotorPort.A);
		MotorB = new EV3LargeRegulatedMotor(MotorPort.B);
		MotorC = new EV3MediumRegulatedMotor(MotorPort.C);
	}
	
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
	
	public void stopDriving() {
		Motor.A.stop(true);
		Motor.B.stop(true);
	}

	public void arm(int length, int speed) {
		MotorC.setSpeed(speed);
		MotorC.rotate(length, true);
		
		//Motor.C.setSpeed(speed);
		//Motor.C.rotate(length);		
	}	
	
	
	public void stopArm() {
		Motor.C.stop(true);
	}
}

