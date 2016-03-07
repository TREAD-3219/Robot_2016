package org.usfirst.frc3219.Robot_2016;

import org.usfirst.frc3219.Robot_2016.subsystems.Camera;
import org.usfirst.frc3219.Robot_2016.subsystems.Climber;
import org.usfirst.frc3219.Robot_2016.subsystems.Drive;
import org.usfirst.frc3219.Robot_2016.subsystems.FeedMech;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;
import org.usfirst.frc3219.Robot_2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static CANTalon driveRightDriveA;
	public static CANTalon driveLeftDriveA;
	public static CANTalon driveRightDriveB;
	public static CANTalon driveLeftDriveB;
	public static RobotDrive driveDriveMotors;
	public static Encoder driveEncoderLeft;
	public static Encoder driveEncoderRight;

	public static CANTalon driveTopShooter;
	public static CANTalon driveBottomShooter;
	public static Counter shooterCounter;

	public static Talon shooterFeederMotor;
	public static DigitalInput feederLimitSwitch;

	public static Talon driveRollerMotorController;
	public static Talon driveMultiToolArmMotor;	
	public static DigitalInput multiToolLimitSwitchHigh;
	public static DigitalInput multiToolLimitSwitchLow;
	public static Encoder sensorsArmEncoder;

	public static Servo climberReleaseServoLeft;
	public static Servo climberReleaseServoRight;

	public static NetworkTable roboRealmTable;
	public static Camera camera;
	
	public static void init(){
		Drive.setupRobotMap();
		Shooter.setupRobotMap();
		FeedMech.setupRobotMap();
		MultiTool.setupRobotMap();
		Climber.setupRobotMap();
       	Camera.setupRobotMap();
	}
}
