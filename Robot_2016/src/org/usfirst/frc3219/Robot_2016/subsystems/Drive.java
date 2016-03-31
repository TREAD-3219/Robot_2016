package org.usfirst.frc3219.Robot_2016.subsystems;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.commands.JoystickDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */
public class Drive extends Subsystem { //encoders measuring about 1/3 real distance
	private static final double MAX_MOTOR_RPM = 4000;
	private static final double WHEEL_DIAMETER = 7.75;
	public static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
	private static final double GEAR_RATIO = 12.75;
	private static final double MAX_WHEEL_RPM = MAX_MOTOR_RPM / GEAR_RATIO;
	public static final double MAX_SPEED_IPM = WHEEL_CIRCUMFERENCE * MAX_WHEEL_RPM;
	public static final double MAX_SPEED_IPS = MAX_SPEED_IPM / 60.0;
	public static final double WHEEL_BASE = 27.75;// distance between the wheel
													// centers
	private static final double ROTATION_CIRCLE = Math.PI * WHEEL_BASE;
	private static final double MAX_TURN_RATE = MAX_SPEED_IPS / ROTATION_CIRCLE;
	public static final double MAX_TURN_RATE_DPS = MAX_TURN_RATE * 360.0;
	public static final double WHEEL_DISTANCE_PER_PULSE = WHEEL_CIRCUMFERENCE / Sensors.WHEEL_ENCODER_PULSE_PER_REVOLUTION;
	public static final double LEFT_ENCODER_CORRECTION = -3.68;
	public static final double RIGHT_ENCODER_CORRECTION = -3.68;
	public static final String DISTANCE_TAG = "Drive Distance";

	CANTalon rightDriveFront = RobotMap.driveRightDriveB;
	CANTalon rightDriveRear = RobotMap.driveRightDriveA;
	CANTalon leftDriveFront = RobotMap.driveLeftDriveB;
	CANTalon leftDriveRear = RobotMap.driveLeftDriveA;
	RobotDrive driveMotors = RobotMap.driveDriveMotors;

	double reverse = 1.0;
	
	public static void setupRobotMap() {
		RobotMap.driveRightDriveA = new CANTalon(4);
		RobotMap.driveRightDriveB = new CANTalon(5);
		RobotMap.driveLeftDriveA = new CANTalon(2);
		RobotMap.driveLeftDriveB = new CANTalon(3);
		RobotMap.driveDriveMotors = new RobotDrive(RobotMap.driveLeftDriveA, RobotMap.driveLeftDriveB,
				RobotMap.driveRightDriveA, RobotMap.driveRightDriveB);
		RobotMap.driveEncoderLeft = new Encoder(0, 1, false, EncodingType.k4X);
		RobotMap.driveEncoderLeft.setDistancePerPulse(-Drive.WHEEL_DISTANCE_PER_PULSE);
		RobotMap.driveEncoderLeft.setPIDSourceType(PIDSourceType.kRate);
		RobotMap.driveEncoderRight = new Encoder(2, 3, false, EncodingType.k4X);
		RobotMap.driveEncoderRight.setDistancePerPulse(Drive.WHEEL_DISTANCE_PER_PULSE);
		RobotMap.driveEncoderRight.setPIDSourceType(PIDSourceType.kRate);
	}

	public Drive() {
		rightDriveRear = RobotMap.driveRightDriveA;
		rightDriveFront = RobotMap.driveRightDriveB;
		leftDriveRear = RobotMap.driveLeftDriveA;
		leftDriveFront = RobotMap.driveLeftDriveB;
		driveMotors = RobotMap.driveDriveMotors;
	}
	
	public void driveValues(double forward, double turnRate){
		driveMotors.arcadeDrive(forward*reverse, turnRate*reverse);
	}
	
	public double getAvgEncoderDist() {
		return (leftEncoderDistance() + rightEncoderDistance()) / 2.0;
	}
	
	public double leftEncoderDistance(){
		double res = Robot.sensors.leftEncoderDistance() * LEFT_ENCODER_CORRECTION; 
		return res;
	}
	
	public double rightEncoderDistance(){
		double res = Robot.sensors.rightEncoderDistance() * RIGHT_ENCODER_CORRECTION; 
		return res;
	}

	public void setSafety(boolean safely) {
		driveMotors.setSafetyEnabled(safely);
	}

	public double powerFromSpeed(double speed) {
		if (speed < 1.0) {
			return 0;
		}
		
		double magnitude = Math.abs(speed);
		double power = -0.0000395 * magnitude * magnitude + 0.0111 * magnitude + 0.22;
		double fixed = Math.min(1.0, power);
		return fixed * Math.signum(speed);
	}

	public void driveSpeed(double forwardSpeed, double turnRate) {
		double power = powerFromSpeed(forwardSpeed);
		double turns = turnRate / MAX_TURN_RATE_DPS;
		driveMotors.drive(power, turns);
	}
	
    public double speedFromPower(double power) {
    	double result = Math.max(0, 71.882*power*power + 39.665* power - 11.083);
    	return result;
    }
    
    public void setBrakesOn() {
		RobotMap.driveLeftDriveA.enableBrakeMode(true);
		RobotMap.driveLeftDriveB.enableBrakeMode(true);
		RobotMap.driveRightDriveA.enableBrakeMode(true);
		RobotMap.driveRightDriveB.enableBrakeMode(true);
    }
    
    public void setBrakesOff() {
		RobotMap.driveLeftDriveA.enableBrakeMode(false);
		RobotMap.driveLeftDriveB.enableBrakeMode(false);
		RobotMap.driveRightDriveA.enableBrakeMode(false);
		RobotMap.driveRightDriveB.enableBrakeMode(false);
    }
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickDrive());
	}
}
