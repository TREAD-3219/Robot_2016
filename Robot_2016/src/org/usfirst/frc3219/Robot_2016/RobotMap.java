// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3219.Robot_2016;

import org.usfirst.frc3219.Robot_2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static Jaguar driveRightDrive;
	public static Jaguar driveLeftDrive;
	public static RobotDrive driveDriveMotors;

	public static Victor driveRollerMotorController;
	public static Victor driveMultiToolArmMotor;

    public static Jaguar spinShooterWheel;
	public static Victor shooterFeeder;
	
	public static CANTalon driveRightDriveShooter;
	public static CANTalon driveLeftDriveShooter;

	public static DigitalInput lineFinder;

	public static Counter normalCounter;
	public static DigitalInput lineSeekerInput;
	
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public static Encoder driveEncoderLeft;
	public static Encoder driveEncoderRight;
	public static DigitalInput multiToolLimitSwitchHigh;
	public static DigitalInput multiToolLimitSwitchLow;
	public static DigitalInput climberLimitSwitch_1;
	public static SpeedController climberSpeed_Controller_12;
	public static DigitalInput climberLimit_Switch_2;
	public static AnalogInput sensorsUltraSonic1;
	public static AnalogInput sensorsUltraSonic2;
	

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public static void init() {
		try {
			//Drive
			driveRightDrive = new Jaguar(1);
			driveLeftDrive = new Jaguar(0);
			driveDriveMotors = new RobotDrive(driveLeftDrive,  driveRightDrive);
			driveDriveMotors.setSafetyEnabled(true);
			driveDriveMotors.setExpiration(0.1);
			driveDriveMotors.setSensitivity(0.5);
			driveDriveMotors.setMaxOutput(1.0);
			//Encoders
			driveEncoderLeft = new Encoder(1, 2, false, EncodingType.k4X);
			driveEncoderLeft.setDistancePerPulse(1.0);
			driveEncoderLeft.setPIDSourceType(PIDSourceType.kRate);
			driveEncoderRight = new Encoder(3, 4, false, EncodingType.k4X);
			driveEncoderRight.setDistancePerPulse(1.0);
			driveEncoderRight.setPIDSourceType(PIDSourceType.kRate);
			//MultiTool
			driveRollerMotorController = new Victor(2);
			driveMultiToolArmMotor = new Victor(3);
			//multiToolLimitSwitchHigh = new DigitalInput(6);
			//multiToolLimitSwitchLow = new DigitalInput(7);
			//Shooter
			driveRightDriveShooter = new CANTalon(4);
			driveLeftDriveShooter = new CANTalon(5);
			
			normalCounter = new Counter(5);
			
			

		} catch (Exception e) {
			System.out.println("ERROR ON THE RobotMapInit: " + e.getMessage());
		}

		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

		//climberLimitSwitch_1 = new DigitalInput(0);
		//LiveWindow.addSensor("Climber", "Limit Switch_1", climberLimitSwitch_1);

		//climberLimit_Switch_2 = new DigitalInput(5);
		//LiveWindow.addSensor("Climber", "Limit_Switch_2", climberLimit_Switch_2);

		//lineSeekerInput = new DigitalInput(4);
		//SmartDashboard.putBoolean(Sensors.LINE_SEEKER_TAG, lineSeekerInput.get());
		
		sensorsUltraSonic1 = new AnalogInput(0);
		LiveWindow.addSensor("Sensors", "Ultrasonic1", sensorsUltraSonic1);
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
	}
}
