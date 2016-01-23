package org.usfirst.frc3219.Robot_2016.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc3219.Robot_2016.RobotMap;


/**
 *
 */
public class Drive extends Subsystem {
	private static final double MAX_MOTOR_RPM = 4000;
	private static final double WHEEL_DIAMETER = 4.0;
	public static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
	private static final double GEAR_RATIO = 8.45;
	private static final double MAX_WHEEL_RPM = MAX_MOTOR_RPM / GEAR_RATIO;
	public static final double MAX_SPEED_IPM = WHEEL_CIRCUMFERENCE * MAX_WHEEL_RPM;
	public static final double MAX_SPEED_IPS = MAX_SPEED_IPM / 60.0;
	public static final double WHEEL_BASE = 27.75;// distance between the wheel centers
	private static final double ROTATION_CIRCLE = Math.PI * WHEEL_BASE;
	private static final double MAX_TURN_RATE = MAX_SPEED_IPS / ROTATION_CIRCLE;
	public static final double MAX_TURN_RATE_DPS = MAX_TURN_RATE * 360.0;
	
	
	CANJaguar rightDriveFront = RobotMap.driveRightDriveFront;
	CANJaguar rightDriveBack = RobotMap.driveRightDriveBack;
	CANJaguar leftDriveFront = RobotMap.driveLeftDriveFront;
	CANJaguar leftDriveBack = RobotMap.driveLeftDriveBack;
	RobotDrive driveMotors = RobotMap.driveDriveMotors;
	
	public void driveValues(double forward, double turnRate){
		driveMotors.arcadeDrive(forward, turnRate);
	}
	
	public void setSafety(boolean safely){
		driveMotors.setSafetyEnabled(safely);
	}
	
	public double powerFromSpeed(double speed){
		double magnitude = Math.abs(speed);
		double power = -0.00003*magnitude*magnitude + 0.0111*magnitude + 0.2176;
		double fixed = Math.min(1.0, power);
		return fixed * Math.signum(speed);
	}
	public void driveSpeed(double forwardSpeed, double turnRate){
		double power = powerFromSpeed(forwardSpeed);
		double turns = turnRate / MAX_TURN_RATE_DPS;
		driveMotors.drive(power, turns);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
}

