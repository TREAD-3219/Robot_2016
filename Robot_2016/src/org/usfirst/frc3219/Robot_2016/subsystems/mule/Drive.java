package org.usfirst.frc3219.Robot_2016.subsystems.mule;

import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.commands.JoystickDrive;
import org.usfirst.frc3219.Robot_2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {
	private static final double MAX_MOTOR_RPM = 4000;
	private static final double WHEEL_DIAMETER = 4;
	public static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
	private static final double GEAR_RATIO = 8.45;
	private static final double MAX_WHEEL_RPM = MAX_MOTOR_RPM / GEAR_RATIO;
	public static final double MAX_SPEED_IPM = WHEEL_CIRCUMFERENCE * MAX_WHEEL_RPM;
	public static final double MAX_SPEED_IPS = MAX_SPEED_IPM / 60.0;
	public static final double WHEEL_BASE = 27.75;// distance between the wheel
													// centers
	private static final double ROTATION_CIRCLE = Math.PI * WHEEL_BASE;
	private static final double MAX_TURN_RATE = MAX_SPEED_IPS / ROTATION_CIRCLE;
	public static final double MAX_TURN_RATE_DPS = MAX_TURN_RATE * 360.0;
	public static final double WHEEL_DISTANCE_PER_PULSE = WHEEL_CIRCUMFERENCE
			/ Sensors.WHEEL_ENCODER_PULSE_PER_REVOLUTION;

	CANTalon rightDrive;
	CANTalon leftDrive;
	RobotDrive driveMotors;

	double reverse = 1.0;

	public static void setupRobotMap() {
		RobotMap.driveRightDriveA = new CANTalon(4);
		RobotMap.driveLeftDriveA = new CANTalon(5);
		RobotMap.driveDriveMotors = new RobotDrive(RobotMap.driveLeftDriveA, RobotMap.driveRightDriveA);
		RobotMap.driveEncoderLeft = new Encoder(0, 1, false, EncodingType.k4X);
		RobotMap.driveEncoderLeft.setDistancePerPulse(Drive.WHEEL_DISTANCE_PER_PULSE);
		RobotMap.driveEncoderLeft.setPIDSourceType(PIDSourceType.kRate);
		RobotMap.driveEncoderRight = new Encoder(2, 3, false, EncodingType.k4X);
		RobotMap.driveEncoderRight.setDistancePerPulse(-Drive.WHEEL_DISTANCE_PER_PULSE);
		RobotMap.driveEncoderRight.setPIDSourceType(PIDSourceType.kRate);
	}

	public Drive() {
		rightDrive = RobotMap.driveRightDriveA;
		leftDrive = RobotMap.driveLeftDriveA;
		driveMotors = RobotMap.driveDriveMotors;
	}
	
	public void driveValues(double forward, double turnRate) {
		driveMotors.arcadeDrive(forward * reverse, turnRate * reverse);
	}

	public void setSafety(boolean safely) {
		driveMotors.setSafetyEnabled(safely);
	}

	public double powerFromSpeed(double speed) {
		double magnitude = Math.abs(speed);
		double power = -0.00003 * magnitude * magnitude + 0.0111 * magnitude + 0.2176;
		double fixed = Math.min(1.0, power);
		return fixed * Math.signum(speed);
	}

	public void driveSpeed(double forwardSpeed, double turnRate) {
		double power = powerFromSpeed(forwardSpeed);
		double turns = turnRate / MAX_TURN_RATE_DPS;
		driveMotors.drive(power, turns);
	}

	public double speedFromPower(double power) {
		double result = Math.max(0, 71.882 * power * power + 39.665 * power - 11.083);
		return result;
	}

	public void setBrakesOn() {
		leftDrive.enableBrakeMode(true);
		rightDrive.enableBrakeMode(true);
	}

	public void setBrakesOff() {
		leftDrive.enableBrakeMode(false);
		rightDrive.enableBrakeMode(false);
	}

	@Override
	protected void initDefaultCommand() {
	}
}
