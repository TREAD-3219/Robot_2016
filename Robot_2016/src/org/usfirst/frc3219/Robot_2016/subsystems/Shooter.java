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

import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {

	public static final String DEFAULT_SHOOTING_DISTANCE_TAG = "Distance From Target";
	public static final double BOTTOM_SHOOTER_SPEED = 1.0;
	public static final double TOP_SHOOTER_SPEED = 0.7;
	private static final double SHOOTER_STOP_SPEED = 0.0;
	private static final int COUNTER_SAMPLES_TO_AVERAGE = 3;
	private static final double COUNTER_MAX_PERIOD = 0.1;
	private static final int COUNTER_DISTANCE_PER_PULSE = 1;
	public static final String TOPSHOOTER = "ShooterTop";
	public static final String BOTTOMSHOOTER = "ShooterBottom";
	private static final double SHOOTER_RPS_MINIMUM = 10; // 600 RPM - adjust this!
	private static final String SHOOTER_MOTORS_TAG = "Shooter Motors";

	private CANTalon shooterTopMotor;
	private CANTalon shooterBottomMotor;
	
	public static void setupRobotMap() {
		RobotMap.driveTopShooter = new CANTalon(6);
		RobotMap.driveBottomShooter = new CANTalon(1);
		RobotMap.shooterCounter = new Counter(8);
		RobotMap.shooterCounter.setDistancePerPulse(1);
	}
	
	public Shooter() {
		shooterTopMotor = RobotMap.driveTopShooter;
		shooterBottomMotor = RobotMap.driveBottomShooter;
	}
	//public double findXPoint() { //YPoint is always 8'1", or 97"
		//double lidar = Robot.sensors.readLidar1();
		//double x = lidar + 5.55079;
		//return x;
	//}
	
	public double findMotorSpeed(double v) { //gets the speed the motor has to shoot to hit a certain point
//		double x = SmartDashboard.getNumber(DEFAULT_SHOOTING_DISTANCE_TAG); //find lidar i guess
//		final int shooterHeight = 27;
//		final int y = 80 - shooterHeight; //inches high of goal
//		double velocity = v;
		double motorSpeed = (v - 30.676620) / 283.740741; //velocity times speed/velocity ratio
		return motorSpeed;
	}
	
	public double findVelocityForPoint(double x, double y) {
		double v;
		double g = 400; //386.088583
		double theta = 54 * (Math.PI / 180);
		v = Math.sqrt(((g * x * x) * (Math.pow(Math.tan(theta), 2) + 1)) / (2 * (Math.tan(theta) * x - y)));
		return v;
	}

	public void init() {
		shooterTopMotor.setSafetyEnabled(false);
		shooterBottomMotor.setSafetyEnabled(false);
		shooterTopMotor.enableBrakeMode(false);
		shooterBottomMotor.enableBrakeMode(false);
		shooterTopMotor.setInverted(false);
		shooterBottomMotor.setInverted(false);

		// set up counter mode
		RobotMap.shooterCounter.setUpDownCounterMode();

		// Reset
		RobotMap.shooterCounter.reset();

		// Counter Setting
		RobotMap.shooterCounter.setMaxPeriod(COUNTER_MAX_PERIOD);
		RobotMap.shooterCounter.setUpdateWhenEmpty(true);
		RobotMap.shooterCounter.setReverseDirection(false);
		RobotMap.shooterCounter.setSamplesToAverage(COUNTER_SAMPLES_TO_AVERAGE);
		RobotMap.shooterCounter.setDistancePerPulse(COUNTER_DISTANCE_PER_PULSE);
		
		SmartDashboard.putNumber(TOPSHOOTER, TOP_SHOOTER_SPEED);
		SmartDashboard.putNumber(BOTTOMSHOOTER, BOTTOM_SHOOTER_SPEED);
		SmartDashboard.putNumber(DEFAULT_SHOOTING_DISTANCE_TAG, 100);
		
	}

	public void spinUp(double TopPower, double BottomPower) {
		this.shooterTopMotor.set(TopPower);
		this.shooterBottomMotor.set(BottomPower * -1);
		SmartDashboard.putBoolean(SHOOTER_MOTORS_TAG, true);
	}

	public void spinDown() {
		this.shooterTopMotor.set(SHOOTER_STOP_SPEED);
		this.shooterBottomMotor.set(SHOOTER_STOP_SPEED);
		SmartDashboard.putBoolean(SHOOTER_MOTORS_TAG, false);
	}

	public void initDefaultCommand() {
	}

	public boolean atSpeed() {
		boolean atMin = RobotMap.shooterCounter.getRate() > SHOOTER_RPS_MINIMUM;
		SmartDashboard.putBoolean("Shooter at speed", atMin);
		return atMin;
	}
}
