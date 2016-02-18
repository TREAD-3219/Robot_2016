package org.usfirst.frc3219.Robot_2016.subsystems;

import org.usfirst.frc3219.Robot_2016.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import Robot_2016.utility.Utility;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Sensors extends Subsystem implements edu.wpi.first.wpilibj.PIDSource {
	private static final byte LIDAR_1_ADDR = 0x62;
	private static final byte LIDAR_READ_START = (byte) 0x8F;
	private static final int START_MEASUREMENT = 0x04;
	private static final int READ_CONTROL_REGISTER = 0x00;

	private static final double RADIANS_TO_DEGREES = 180 / Math.PI;
	private static final double TICKS_PER_REVOLUTION = 360.0;
	private static final double DISTANCE_PER_PULSE = Drive.WHEEL_CIRCUMFERENCE / TICKS_PER_REVOLUTION;
	private static final double LEFT_CALIBRATION = -1.0;
	private static final double RIGHT_CALIBRATION = 1.0;
	private static final double LEFT_DISTANCE_PER_PULSE = DISTANCE_PER_PULSE * LEFT_CALIBRATION;
	private static final double RIGHT_DISTANCE_PER_PULSE = DISTANCE_PER_PULSE * RIGHT_CALIBRATION;

	public static final String TACH_RAW = "Tach_Raw";
	public static final String LINE_SEEKER_TAG = "Line Seeker";
	public static final String COMPASS_TAG = "Compass";
	public static final String LIDAR_TAG = "Lidar1";
	public static final String SHOOTER_RPM_TAG = "Shooter RPM";
	public static final String ULTRASONIC_TAG = "Ultra1";
	public static final String ROTATION_COUNTER_TAG = "RotationCounter";

	PowerDistributionPanel pdp = new PowerDistributionPanel();
	I2C lidar1 = new I2C(I2C.Port.kMXP, LIDAR_1_ADDR);
	public static AHRS navx = new AHRS(Port.kMXP);
	AnalogInput ultrasonic1 = RobotMap.sensorsUltraSonic1;

	Encoder rightEncoder = RobotMap.driveEncoderRight;
	Encoder leftEncoder = RobotMap.driveEncoderLeft;
	Encoder armEncoder = RobotMap.sensorsArmEncoder;

	EncoderData rightData = new EncoderData(rightEncoder);
	EncoderData leftData = new EncoderData(leftEncoder);
	EncoderData armData = new EncoderData(armEncoder);

	double lastLidar1Read = 0.0;


	public void sensorReset(){
		leftEncoder.reset();
		rightEncoder.reset();
		armEncoder.reset();
		leftData.reset();
		rightData.reset();
		armData.reset();
		this.lastLidar1Read = 0.0;
	}
	

	private void startLidarMeasurement() {
		boolean res = lidar1.write(READ_CONTROL_REGISTER, START_MEASUREMENT);
		SmartDashboard.putBoolean("Start Lidar", res);
	}

	public double readLidar1() {
		byte[] bytes = new byte[2];
		double res = -1.0;
		// read the data from the last measure command
		if (lidar1.read(LIDAR_READ_START, 2, bytes)) {

			int cms = Utility.getShort(bytes, 0);
			res = cms / 2.54;
			if (res != 0) {
				lastLidar1Read = res;
			} else {
				res = lastLidar1Read;
			}
			startLidarMeasurement();
		}

		return res;
	}

	public double getTip() {
		return Math.abs(org.usfirst.frc3219.Robot_2016.Robot.sensors.navx.getPitch())
				+ Math.abs(org.usfirst.frc3219.Robot_2016.Robot.sensors.navx.getRoll());
	}


	public double getAngle() {

		return this.navx.getAngle();
	}

	public double readShooterCounter1() {
		return RobotMap.normalCounter.getPeriod();
	}

	public void getCounterValues() {
		int count = RobotMap.normalCounter.get();
		SmartDashboard.putNumber(TACH_RAW, count);
		double distance = RobotMap.normalCounter.getDistance();
		double period = RobotMap.normalCounter.getPeriod();
		double rate = RobotMap.normalCounter.getRate();
		boolean direction = RobotMap.normalCounter.getDirection();
		boolean stopped = RobotMap.normalCounter.getStopped();
	}

	public boolean readLineSeeker() {
		return RobotMap.lineSeekerInput.get();
	}

	public double readUltraSonic1() {
		double ultraValue1 = RobotMap.sensorsUltraSonic1.getVoltage() * 0.0000384251985;
		return ultraValue1;
	}

	public int readShooterCounter() {
		return RobotMap.normalCounter.get();
	}

	
	public double aveDistEncoder(){
		return(RobotMap.driveEncoderLeft.getDistance() + RobotMap.driveEncoderRight.getDistance()) / 2;
	}

	public int leftEncoderRaw(){
		return leftEncoder.getRaw() / leftEncoder.getEncodingScale();
	}
	
	public int rightEncoderRaw(){
		return rightEncoder.getRaw() / rightEncoder.getEncodingScale();
	}
	
	public int armEncoderRaw(){
		return armEncoder.getRaw() / rightEncoder.getEncodingScale();	
	}
	
	public double leftEncoderDistance(){
		double res = leftEncoder.getDistance();
		return res;
	}
	
	public double rightEncoderDistance(){
		double res = rightEncoder.getDistance();
		return res;
	}
	
	public double armEncoderDistance(){
		double res = armEncoder.getDistance();
		return res;
	}
	
	public double leftEncoderSpeed() {
		double res = leftEncoder.getRate();
		return res;
	}

	public double rightEncoderSpeed() {
		double res = rightEncoder.getRate();
		return res;
	}
	
	public double armEncoderSpeed(){
		double res = armEncoder.getRate();
		return res;
	}
	
	public double armEncoderAngle() {
		return armEncoder.getDistance();
	}

	public double getAvgEncoderDist() {
		return (leftEncoderDistance() + rightEncoderDistance()) / 2.0;
	}

	public double getVelocity() {
		return (leftEncoderSpeed() + rightEncoderSpeed()) / 2.0;
	}

	public void thetaReset() {
		leftData.reset();
		rightData.reset();
	}

	public double getTheta() {
		double lRDelta = leftData.getDeltaDistance() - rightData.getDeltaDistance();
		double theta = lRDelta / Drive.WHEEL_BASE;
		double finalTheta = theta * Sensors.RADIANS_TO_DEGREES;
		return finalTheta % 360.0;
	}

	public double getThetaDot() {
		double lRDelta = leftData.getDeltaVelocity() - rightData.getDeltaVelocity();
		double theta = lRDelta / Drive.WHEEL_BASE;
		return theta * Sensors.RADIANS_TO_DEGREES;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub


	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double pidGet() {

		return 0;
	}

	@Override
	public void setPIDSourceType(PIDSourceType arg0) {
		// TODO Auto-generated method stub

	}

	private class EncoderData {
		Encoder _myEncoder;
		double _baseDistance;
		double _baseVelocity;

		public EncoderData(Encoder e) {
			_myEncoder = e;
			this.reset();
		}

		public void reset() {
			_baseDistance = _myEncoder.getDistance();
			_baseVelocity = _myEncoder.getRate();
		}

		public double getDeltaDistance() {
			double deltaD = _myEncoder.getDistance() - _baseDistance;
			return deltaD;
		}

		public double getDeltaVelocity() {
			double deltaV = _myEncoder.getRate() - _baseVelocity;
			return deltaV;
		}
	}
}
