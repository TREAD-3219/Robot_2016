package org.usfirst.frc3219.Robot_2016;

import org.usfirst.frc3219.Robot_2016.subsystems.Camera;
import org.usfirst.frc3219.Robot_2016.subsystems.Drive;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
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

	public static CANTalon driveTopShooter;
	public static CANTalon driveBottomShooter;
	public static DigitalInput feederLimitSwitch;

	public static Servo pwmServo_4;
	public static Servo pwmServo_3;
	public static Servo testServo;

	public static Talon driveRollerMotorController;
	public static Talon driveMultiToolArmMotor;
	public static Talon shooterFeederMotor;

	public static DigitalInput multiToolLimitSwitchHigh;
	public static DigitalInput multiToolLimitSwitchLow;

	public static Encoder driveEncoderLeft;
	public static Encoder driveEncoderRight;

	public static Encoder sensorsArmEncoder;

	public static Camera camera;

	public static Counter shooterCounter;

	// For commands and RPM.
	public static double time;

	public static Counter normalCounter;
	
    public static NetworkTable roboRealmTable;
	
	public static void init(){
		driveRightDriveA  = new CANTalon(4);
		driveRightDriveB  = new CANTalon(5);
		driveLeftDriveA  = new CANTalon(2);
		driveLeftDriveB  = new CANTalon(3);
		driveDriveMotors = new RobotDrive(driveLeftDriveA, driveLeftDriveB, driveRightDriveA, driveRightDriveB);

		driveTopShooter = new CANTalon(6);
		driveBottomShooter = new CANTalon(1);

		shooterFeederMotor = new Talon(0);
		feederLimitSwitch = new DigitalInput(9);

		pwmServo_4 = new Servo(4);// these might be wrong 2/23/16 after merge
		pwmServo_3 = new Servo(5);

		driveRollerMotorController = new Talon(1);
		driveMultiToolArmMotor = new Talon(2);
		multiToolLimitSwitchHigh = new DigitalInput(6);
		multiToolLimitSwitchLow = new DigitalInput(7);
		
		shooterCounter = new Counter(8);
		shooterCounter.setDistancePerPulse(1);

		roboRealmTable = NetworkTable.getTable("SmartDashboard");
		
		driveEncoderLeft = new Encoder(0, 1, false, EncodingType.k4X);
		driveEncoderLeft.setDistancePerPulse(-0.067638888);
		driveEncoderLeft.setPIDSourceType(PIDSourceType.kRate);
		driveEncoderRight = new Encoder(2, 3, false, EncodingType.k4X);
		driveEncoderRight.setDistancePerPulse(0.067638888);
		driveEncoderRight.setPIDSourceType(PIDSourceType.kRate);
		sensorsArmEncoder = new Encoder(4, 5, false, EncodingType.k4X);
		sensorsArmEncoder.setDistancePerPulse(0.72434); //TODO FIX THIS VALUE
		sensorsArmEncoder.setPIDSourceType(PIDSourceType.kRate);
		
       	roboRealmTable = NetworkTable.getTable("SmartDashboard");

		camera = new Camera();
	}
}
