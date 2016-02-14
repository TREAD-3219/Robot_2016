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

import org.usfirst.frc3219.Robot_2016.subsystems.Camera;
import org.usfirst.frc3219.Robot_2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PWM;
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
	
	public static Camera camera;
	
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
	
	//For ServoController
	public static PWM pwmServo_4;
	
	
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
			multiToolLimitSwitchLow = new DigitalInput(8);
			multiToolLimitSwitchHigh = new DigitalInput(9);

			//Shooter
			driveRightDriveShooter = new CANTalon(4);
			driveLeftDriveShooter = new CANTalon(5);
			
			normalCounter = new Counter(5);
			
			//quick release / climber
			pwmServo_4 = new PWM(4);
			
			

		} catch (Exception e) {
			System.out.println("ERROR ON THE RobotMapInit: " + e.getMessage());
		}

		 //BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

		/*
		baseQuadrature_Encoder_1 = new Encoder(1, 2, false, EncodingType.k4X);
		LiveWindow.addSensor("Base", "Quadrature_Encoder_1", baseQuadrature_Encoder_1);
		baseQuadrature_Encoder_1.setDistancePerPulse(1.0);
		baseQuadrature_Encoder_1.setPIDSourceType(PIDSourceType.kRate);
		baseQuadrature_Encoder_2 = new Encoder(3, 4, false, EncodingType.k4X);
		LiveWindow.addSensor("Base", "Quadrature_Encoder_2", baseQuadrature_Encoder_2);
		baseQuadrature_Encoder_2.setDistancePerPulse(1.0);
		baseQuadrature_Encoder_2.setPIDSourceType(PIDSourceType.kRate);
		*/
		
		
		
		
		/*
		 * baseSpeed_Controller_0 = new CANJaguar(10);
		 * LiveWindow.addActuator("Base", "Speed_Controller_0", (CANJaguar)
		 * baseSpeed_Controller_0);
		 * 
		 * baseSpeed_Controller_1 = new CANJaguar(11);
		 * LiveWindow.addActuator("Base", "Speed_Controller_1", (CANJaguar)
		 * baseSpeed_Controller_1);
		 * 
		 * baseSpeed_Controller_2 = new CANJaguar(12);
		 * LiveWindow.addActuator("Base", "Speed_Controller_2", (CANJaguar)
		 * baseSpeed_Controller_2);
		 * 
		 * baseSpeed_Controller_3 = new CANJaguar(13);
		 * LiveWindow.addActuator("Base", "Speed_Controller_3", (CANJaguar)
		 * baseSpeed_Controller_3);
		 * 
		 * baseRobotDrive41 = new RobotDrive(baseSpeed_Controller_0,
		 * baseSpeed_Controller_1, baseSpeed_Controller_2,
		 * baseSpeed_Controller_3);
		 * 
		 * baseRobotDrive41.setSafetyEnabled(true);
		 * baseRobotDrive41.setExpiration(0.1);
		 * baseRobotDrive41.setSensitivity(0.5);
		 * baseRobotDrive41.setMaxOutput(1.0);
		 * baseRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kFrontLeft,
		 * true);
		 * baseRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kRearLeft,
		 * true);
		 */
		/*
		 * shooterSpeed_Controller_4 = new CANJaguar(14);
		 * LiveWindow.addActuator("Shooter", "Speed_Controller_4", (CANJaguar)
		 * shooterSpeed_Controller_4);
		 * 
		 * shooterSpeed_Controller_5 = new CANJaguar(15);
		 * LiveWindow.addActuator("Shooter", "Speed_Controller_5", (CANJaguar)
		 * shooterSpeed_Controller_5);
		 * 
		 * shooterRobotDrive21 = new RobotDrive(shooterSpeed_Controller_4,
		 * shooterSpeed_Controller_5);
		 * 
		 * shooterRobotDrive21.setSafetyEnabled(true);
		 * shooterRobotDrive21.setExpiration(0.1);
		 * shooterRobotDrive21.setSensitivity(0.5);
		 * shooterRobotDrive21.setMaxOutput(1.0);
		 * shooterRobotDrive21.setInvertedMotor(RobotDrive.MotorType.kRearLeft,
		 * true);
		 */
		/*
		 * feedMechSpeed_Controller_6 = new CANJaguar(16);
		 * LiveWindow.addActuator("Feed Mech", "Speed_Controller_6", (CANJaguar)
		 * feedMechSpeed_Controller_6);
		 * 
		 * feedMechSpeed_Controller_7 = new CANJaguar(7);
		 * LiveWindow.addActuator("Feed Mech", "Speed_Controller_7", (CANJaguar)
		 * feedMechSpeed_Controller_7);
		 * 
		 * feedMechRobotDrive21 = new RobotDrive(feedMechSpeed_Controller_6,
		 * feedMechSpeed_Controller_7);
		 * 
		 * feedMechRobotDrive21.setSafetyEnabled(true);
		 * feedMechRobotDrive21.setExpiration(0.1);
		 * feedMechRobotDrive21.setSensitivity(0.5);
		 * feedMechRobotDrive21.setMaxOutput(1.0);
		 */

		multiToolLimitSwitchLow = new DigitalInput(8);
		LiveWindow.addSensor("multiTool", "Limit_Switch_1", multiToolLimitSwitchLow);
		
		multiToolLimitSwitchHigh = new DigitalInput(9);
		LiveWindow.addSensor("multiTool", "Limit_Switch_2", multiToolLimitSwitchHigh);
		/*
		 * multiToolSpeed_Controller_11 = new CANJaguar(11);
		 * LiveWindow.addActuator("multiTool", "Speed_Controller_11",
		 * (CANJaguar) multiToolSpeed_Controller_11);
		 */

		// multiToolRollerSpeedController = new CANJaguar(9);
		// LiveWindow.addActuator("multiToolRoller", "Speed_Controller_9",
		// (CANJaguar) multiToolRollerSpeedController);

		


		climberLimitSwitch_1 = new DigitalInput(0);
		LiveWindow.addSensor("Climber", "Limit Switch_1", climberLimitSwitch_1);

		climberLimit_Switch_2 = new DigitalInput(8);
		LiveWindow.addSensor("Climber", "Limit_Switch_2", climberLimit_Switch_2);

		lineSeekerInput = new DigitalInput(9);
		SmartDashboard.putBoolean(Sensors.LINE_SEEKER_TAG, lineSeekerInput.get());
		
		sensorsUltraSonic1 = new AnalogInput(0);
		LiveWindow.addSensor("Sensors", "Ultrasonic1", sensorsUltraSonic1);
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
	}
}
