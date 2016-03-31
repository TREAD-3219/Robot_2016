package org.usfirst.frc3219.Robot_2016.subsystems;

import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.utility.Utility;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Sensors extends Subsystem implements edu.wpi.first.wpilibj.PIDSource {
	private static final byte LIDAR_1_ADDR = 0x62;
	private static final byte LIDAR_READ_START = (byte) 0x8F;
	private static final int START_MEASUREMENT = 0x04;
	private static final int READ_CONTROL_REGISTER = 0x00;

	private static final double RADIANS_TO_DEGREES = 180 / Math.PI;

	public static final double WHEEL_ENCODER_PULSE_PER_REVOLUTION = 360.0 * 4; // encoders counting all edges

	public static final String TACH_RAW = "Tach_Raw";
	public static final String LINE_SEEKER_TAG = "Line Seeker";
	public static final String COMPASS_TAG = "Compass";
	public static final String LIDAR_TAG = "Lidar1";
	public static final String SHOOTER_RPM_TAG = "Shooter RPM";
	public static final String ULTRASONIC_TAG = "Ultra1";
	public static final String ROTATION_COUNTER_TAG = "RotationCounter";
	public static final String LEFT_ENCODER_TAG = "Left Encoder";
	public static final String RIGHT_ENCODER_TAG = "Right Encoder";
	public static final String ANGLE = "Angle";
	public static final String SHOOTER_SPEED = "Shooter Speed";

	PowerDistributionPanel pdp = new PowerDistributionPanel();
	I2C lidar1 = new I2C(I2C.Port.kMXP, LIDAR_1_ADDR);
	public AHRS navx = new AHRS(Port.kMXP);

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

	public double getShooterSpeed() {
		return RobotMap.shooterCounter.getRate();
	}

	public int readShooterCounter() {
		return RobotMap.shooterCounter.get();
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
		double res = -leftEncoder.getDistance(); // POSITIVE FOR COMPETITION VERSION
		return res;
	}
	
	public double rightEncoderDistance(){
		double res = -rightEncoder.getDistance(); // POSITIVE FOR COMPETITION VERSION
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
