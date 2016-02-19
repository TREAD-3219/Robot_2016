package org.usfirst.frc3219.Robot_2016;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;

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
	
	public static void init(){
		driveRightDriveA  = new CANTalon(1);
		driveRightDriveB  = new CANTalon(2);
		driveLeftDriveA  = new CANTalon(3);
		driveLeftDriveB  = new CANTalon(4);
		driveDriveMotors = new RobotDrive(driveLeftDriveA, driveLeftDriveB, driveRightDriveA, driveRightDriveB);
	}

	
}
