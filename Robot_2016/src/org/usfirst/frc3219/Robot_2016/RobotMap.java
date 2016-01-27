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

import edu.wpi.first.wpilibj.CANJaguar;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.CANJaguar;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static CANJaguar driveRightDriveFront;
	public static CANJaguar driveRightDriveBack;
	public static CANJaguar driveLeftDriveFront;
	public static CANJaguar driveLeftDriveBack;
	public static RobotDrive driveDriveMotors;
	
	public static CANJaguar driveRollerMotorController;
	public static CANJaguar driveMultiToolMotor1;
	public static CANJaguar driveMultiToolMotor2;
	public static RobotDrive driveMultiTool;
	
	public static Jaguar spinShooterWheel;
	public static Jaguar shooterFeeder;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Encoder baseQuadrature_Encoder_1;
    public static Encoder baseQuadrature_Encoder_2;
    //public static SpeedController baseSpeed_Controller_0;
    //public static SpeedController baseSpeed_Controller_1;
    //public static SpeedController baseSpeed_Controller_2;
    //public static SpeedController baseSpeed_Controller_3;
    //public static RobotDrive baseRobotDrive41;
    //public static SpeedController shooterSpeed_Controller_4;
    //public static SpeedController shooterSpeed_Controller_5;
    //public static RobotDrive shooterRobotDrive21;
    //public static SpeedController feedMechSpeed_Controller_6;
    //public static SpeedController feedMechSpeed_Controller_7;
    //public static RobotDrive feedMechRobotDrive21;
    public static DigitalInput multiToolLimit_Switch_1;
    //public static SpeedController multiToolSpeed_Controller_11;
    //public static SpeedController multiToolRollerSpeedController;
    public static DigitalInput multiToolLimit_Switch_2;
    public static DigitalInput climberLimitSwitch_1;
    public static SpeedController climberSpeed_Controller_12;
    public static DigitalInput climberLimit_Switch_2;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
    	driveRightDriveFront = new CANJaguar(3);// these might be wrong
    	driveRightDriveBack = new CANJaguar(4);
    	
    	driveLeftDriveFront = new CANJaguar(1);
    	driveLeftDriveBack = new CANJaguar(2);
    	
    	driveDriveMotors = new RobotDrive(driveLeftDriveFront, driveLeftDriveBack, driveRightDriveFront, driveRightDriveBack);
    	

    	driveRollerMotorController = new CANJaguar(5);
    	
    	driveMultiToolMotor1 = new CANJaguar(6);
    	driveMultiToolMotor2 = new CANJaguar(7);

    	driveDriveMotors.setSafetyEnabled(true);
    	driveDriveMotors.setExpiration(0.1);
    	driveDriveMotors.setSensitivity(0.5);
    	driveDriveMotors.setMaxOutput(1.0);
    	driveDriveMotors.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
    	driveDriveMotors.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
    	
    	driveRollerMotorController = new CANJaguar(4);
    	
    	driveMultiToolMotor1 = new CANJaguar(5);
    	driveMultiToolMotor2 = new CANJaguar(6);
    	driveMultiTool = new RobotDrive(driveMultiToolMotor1, driveMultiToolMotor2);
    	
    	spinShooterWheel = new Jaguar(0);
    	spinShooterWheel.setSafetyEnabled(true);
   	 	spinShooterWheel.setExpiration(0.1);
    	shooterFeeder = new Jaguar(1);
    	shooterFeeder.setSafetyEnabled(true);
    	shooterFeeder.setExpiration(0.1);
    	   	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        baseQuadrature_Encoder_1 = new Encoder(1, 2, false, EncodingType.k4X);
        LiveWindow.addSensor("Base", "Quadrature_Encoder_1", baseQuadrature_Encoder_1);
        baseQuadrature_Encoder_1.setDistancePerPulse(1.0);
        baseQuadrature_Encoder_1.setPIDSourceType(PIDSourceType.kRate);
        baseQuadrature_Encoder_2 = new Encoder(3, 4, false, EncodingType.k4X);
        LiveWindow.addSensor("Base", "Quadrature_Encoder_2", baseQuadrature_Encoder_2);
        baseQuadrature_Encoder_2.setDistancePerPulse(1.0);
        baseQuadrature_Encoder_2.setPIDSourceType(PIDSourceType.kRate);
        /*baseSpeed_Controller_0 = new CANJaguar(10);
        LiveWindow.addActuator("Base", "Speed_Controller_0", (CANJaguar) baseSpeed_Controller_0);
        
        baseSpeed_Controller_1 = new CANJaguar(11);
        LiveWindow.addActuator("Base", "Speed_Controller_1", (CANJaguar) baseSpeed_Controller_1);
        
        baseSpeed_Controller_2 = new CANJaguar(12);
        LiveWindow.addActuator("Base", "Speed_Controller_2", (CANJaguar) baseSpeed_Controller_2);
        
        baseSpeed_Controller_3 = new CANJaguar(13);
        LiveWindow.addActuator("Base", "Speed_Controller_3", (CANJaguar) baseSpeed_Controller_3);
        
        baseRobotDrive41 = new RobotDrive(baseSpeed_Controller_0, baseSpeed_Controller_1,
              baseSpeed_Controller_2, baseSpeed_Controller_3);
        
        baseRobotDrive41.setSafetyEnabled(true);
        baseRobotDrive41.setExpiration(0.1);
        baseRobotDrive41.setSensitivity(0.5);
        baseRobotDrive41.setMaxOutput(1.0);
        baseRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        baseRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);*/
       /* shooterSpeed_Controller_4 = new CANJaguar(14);
        LiveWindow.addActuator("Shooter", "Speed_Controller_4", (CANJaguar) shooterSpeed_Controller_4);
        
        shooterSpeed_Controller_5 = new CANJaguar(15);
        LiveWindow.addActuator("Shooter", "Speed_Controller_5", (CANJaguar) shooterSpeed_Controller_5);
        
        shooterRobotDrive21 = new RobotDrive(shooterSpeed_Controller_4, shooterSpeed_Controller_5);
        
        shooterRobotDrive21.setSafetyEnabled(true);
        shooterRobotDrive21.setExpiration(0.1);
        shooterRobotDrive21.setSensitivity(0.5);
        shooterRobotDrive21.setMaxOutput(1.0);
        shooterRobotDrive21.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);*/
      /*  feedMechSpeed_Controller_6 = new CANJaguar(16);
        LiveWindow.addActuator("Feed Mech", "Speed_Controller_6", (CANJaguar) feedMechSpeed_Controller_6);
        
        feedMechSpeed_Controller_7 = new CANJaguar(7);
        LiveWindow.addActuator("Feed Mech", "Speed_Controller_7", (CANJaguar) feedMechSpeed_Controller_7);
        
        feedMechRobotDrive21 = new RobotDrive(feedMechSpeed_Controller_6, feedMechSpeed_Controller_7);
        
        feedMechRobotDrive21.setSafetyEnabled(true);
        feedMechRobotDrive21.setExpiration(0.1);
        feedMechRobotDrive21.setSensitivity(0.5);
        feedMechRobotDrive21.setMaxOutput(1.0);*/

        multiToolLimit_Switch_1 = new DigitalInput(6);
        LiveWindow.addSensor("multiTool", "Limit_Switch_1", multiToolLimit_Switch_1);
        
      /*multiToolSpeed_Controller_11 = new CANJaguar(11);
        LiveWindow.addActuator("multiTool", "Speed_Controller_11", (CANJaguar) multiToolSpeed_Controller_11);*/
        
        //multiToolRollerSpeedController = new CANJaguar(9);
        //LiveWindow.addActuator("multiToolRoller", "Speed_Controller_9", (CANJaguar) multiToolRollerSpeedController);
        
        multiToolLimit_Switch_2 = new DigitalInput(7);
        LiveWindow.addSensor("multiTool", "Limit_Switch_2", multiToolLimit_Switch_2);
        
        climberLimitSwitch_1 = new DigitalInput(0);
        LiveWindow.addSensor("Climber", "Limit Switch_1", climberLimitSwitch_1);
        
        climberSpeed_Controller_12 = new CANJaguar(8);
        LiveWindow.addActuator("Climber", "Speed_Controller_12", (CANJaguar) climberSpeed_Controller_12);
        
        climberLimit_Switch_2 = new DigitalInput(5);
        LiveWindow.addSensor("Climber", "Limit_Switch_2", climberLimit_Switch_2);
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
