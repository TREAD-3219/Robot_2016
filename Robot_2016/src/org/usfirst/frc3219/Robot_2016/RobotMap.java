package org.usfirst.frc3219.Robot_2016;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	public static CANTalon driveRightDriveA;
	public static CANTalon driveLeftDriveA;
	public static CANTalon driveRightDriveB;
	public static CANTalon driveLeftDriveB;
	public static RobotDrive driveDriveMotors;
	
	public static CANTalon driveTopShooter;
	public static CANTalon driveBottomShooter;
	public static Victor shooterFeeder;
	public static DigitalInput feederLimitSwitch;
	
	public static void init(){
		driveRightDriveA  = new CANTalon(4);
		driveRightDriveB  = new CANTalon(5);
		driveLeftDriveA  = new CANTalon(2);
		driveLeftDriveB  = new CANTalon(3);
		driveDriveMotors = new RobotDrive(driveLeftDriveA, driveLeftDriveB, driveRightDriveA, driveRightDriveB);
		
		driveTopShooter = new CANTalon(6);
		driveBottomShooter = new CANTalon(1);
		
		shooterFeeder = new Victor(0);
		feederLimitSwitch = new DigitalInput(9);
	}

	
}
