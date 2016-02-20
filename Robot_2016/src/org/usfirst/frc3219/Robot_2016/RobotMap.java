package org.usfirst.frc3219.Robot_2016;

import org.usfirst.frc3219.Robot_2016.subsystems.Camera;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
=======
import edu.wpi.first.wpilibj.Talon;
>>>>>>> Fresh-Start

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
	public static Talon shooterFeeder;
	public static DigitalInput feederLimitSwitch;
	
	public static Servo pwmServo_4;
	public static Servo pwmServo_5;
	
	public static Talon driveRollerMotorController;
	public static Talon driveMultiToolArmMotor;
	public static DigitalInput multiToolLimitSwitchHigh;
	public static DigitalInput multiToolLimitSwitchLow;
	
	public static Encoder driveEncoderLeft;
	public static Encoder driveEncoderRight;
	public static Encoder sensorsArmEncoder;
	
	public static Camera camera;
	public static Counter normalCounter;
	
<<<<<<< HEAD
	public static NetworkTable roboRealmTable;
=======
    public static NetworkTable roboRealmTable;
>>>>>>> Fresh-Start
	
	public static void init(){
		driveRightDriveA  = new CANTalon(4);
		driveRightDriveB  = new CANTalon(5);
		driveLeftDriveA  = new CANTalon(2);
		driveLeftDriveB  = new CANTalon(3);
		driveDriveMotors = new RobotDrive(driveLeftDriveA, driveLeftDriveB, driveRightDriveA, driveRightDriveB);
		
		driveTopShooter = new CANTalon(6);
		driveBottomShooter = new CANTalon(1);
		
		shooterFeeder = new Talon(0);
		feederLimitSwitch = new DigitalInput(9);
		
		pwmServo_4 = new Servo(4);
		pwmServo_5 = new Servo(5);
		
		driveRollerMotorController = new Talon(1);
		driveMultiToolArmMotor = new Talon(2);
		multiToolLimitSwitchHigh = new DigitalInput(6);
		multiToolLimitSwitchLow = new DigitalInput(7);
		
		driveEncoderLeft = new Encoder(0, 1, false, EncodingType.k4X);
		driveEncoderLeft.setDistancePerPulse(1.0);
		driveEncoderLeft.setPIDSourceType(PIDSourceType.kRate);
		driveEncoderRight = new Encoder(2, 3, false, EncodingType.k4X);
		driveEncoderRight.setDistancePerPulse(1.0);
		driveEncoderRight.setPIDSourceType(PIDSourceType.kRate);
		sensorsArmEncoder = new Encoder(4, 5, false, EncodingType.k4X);
		sensorsArmEncoder.setDistancePerPulse(0.72434);
		sensorsArmEncoder.setPIDSourceType(PIDSourceType.kRate);
		
		normalCounter = new Counter(8);
		
<<<<<<< HEAD
		roboRealmTable = NetworkTable.getTable("SmartDashboard");
		camera = new Camera();
		
=======
       	roboRealmTable = NetworkTable.getTable("SmartDashboard");
		camera = new Camera();
>>>>>>> Fresh-Start
	}
}
