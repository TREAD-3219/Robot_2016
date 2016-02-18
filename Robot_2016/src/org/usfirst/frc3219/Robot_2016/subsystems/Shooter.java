// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3219.Robot_2016.subsystems;

import java.awt.Point;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {

	public static final String TOPSHOOTER = "ShooterTop";
	public static final String BOTTOMSHOOTER = "ShooterBottom";

	CANTalon shooterTopMotor = RobotMap.driveLeftDriveShooter;
	CANTalon shooterBottomMotor = RobotMap.driveRightDriveShooter;
	
	public double findXPoint() { //YPoint is always 8'1", or 97"
		double lidar = Robot.sensors.readLidar1();
		double x = lidar + 5.55079;
		return x;
	}
	
	public double findMotorSpeed(double v) { //gets the speed the motor has to shoot to hit a certain point
		double motorSpeed = 0; //velocity times speed/velocity ratio
		return motorSpeed;
	}
	
	public double findVelocityForPoint(double x, double y) {
		double v;
		double g = 386.088583;
		double theta = 45 * (Math.PI / 180);
		v = Math.sqrt(((g * x * x) * (Math.pow(Math.tan(theta), 2) + 1)) / (2 * (Math.tan(theta) * x - y)));
		return v;
	}

	public void init() {
		RobotMap.driveDriveMotors.setSafetyEnabled(false);
		RobotMap.driveDriveMotors.setSensitivity(0.5);
		RobotMap.driveDriveMotors.setMaxOutput(1.0);
		shooterTopMotor.setSafetyEnabled(false);
		shooterBottomMotor.setSafetyEnabled(false);
		shooterTopMotor.enableBrakeMode(false);
		shooterBottomMotor.enableBrakeMode(false);

		// set up counter mode
		RobotMap.normalCounter.setUpDownCounterMode();

		// Reset
		RobotMap.normalCounter.reset();

		// Counter Setting
		RobotMap.normalCounter.setMaxPeriod(0.1);
		RobotMap.normalCounter.setUpdateWhenEmpty(true);
		RobotMap.normalCounter.setReverseDirection(false);
		RobotMap.normalCounter.setSamplesToAverage(10);
		RobotMap.normalCounter.setDistancePerPulse(12);
		
		SmartDashboard.putNumber(TOPSHOOTER, 1.0);
		SmartDashboard.putNumber(BOTTOMSHOOTER, 1.0);
	}

	public void spinUp(double TopPower, double BottomPower) {
		this.shooterTopMotor.set(TopPower);
		this.shooterBottomMotor.set(BottomPower);
	}

	public void spinDown() {
		this.shooterBottomMotor.set(0.0);
		this.shooterTopMotor.set(0.0);
	}

	public void initDefaultCommand() {

	}
}
