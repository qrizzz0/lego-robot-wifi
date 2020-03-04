import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;;

public class Sensors {
		EV3UltrasonicSensor IRSensor, USSensor;
		SampleProvider irSP,usSP;
		float[] irSample, usSample;

	public void initSensors() {
		IRSensor = new EV3UltrasonicSensor(SensorPort.S1);
		USSensor = new EV3UltrasonicSensor(SensorPort.S2);
		
		irSP = IRSensor.getDistanceMode();
		usSP = USSensor.getDistanceMode();
		
		irSample = new float [irSP.sampleSize()];
		usSample = new float [usSP.sampleSize()];
	}
	
	public float readIRSensor() {
		irSP.fetchSample(irSample, 0);
		return irSample[0];
	}
	
	public float readUSSensor() {
		usSP.fetchSample(usSample, 0);
		return usSample[0];
	}
	
}
