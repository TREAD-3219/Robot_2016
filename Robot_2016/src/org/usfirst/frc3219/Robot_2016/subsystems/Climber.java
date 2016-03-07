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

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climber extends Subsystem {

	public static final String CLIMBER_RESET_TAG = "Climber Reset";
	public static final String CLIMBER_RELEASED_TAG = "Climber Released";
	
	private static final double LEFT_RELEASE = 1.0;
	private static final double RIGHT_RELEASE = -1.0;
	private static final double LEFT_RESET = 0;
	private static final double RIGHT_RESET = 1.0;
	public static final float RIGHT_SERVO_CLOSED = 180.0f;
	public static final float LEFT_SERVO_CLOSED = 0.0f;
	public static final float RIGHT_SERVO_OPEN = 0.0f;
	public static final float LEFT_SERVO_OPEN = 180.0f;
	
	Servo leftServo;
	Servo rightServo;
	
	public Climber() {
		leftServo = RobotMap.climberReleaseServoLeft;
		rightServo = RobotMap.climberReleaseServoRight;
		
	}
	
	public void releaseClimber() {
		SmartDashboard.putBoolean(CLIMBER_RELEASED_TAG, true);
		leftServo.set(LEFT_RELEASE);
		rightServo.set(RIGHT_RELEASE);
	}

	public void resetClimber() {
		SmartDashboard.putBoolean(CLIMBER_RESET_TAG, true);
		leftServo.set(LEFT_RESET);
		rightServo.set(RIGHT_RESET);
	}
	
	public void initDefaultCommand() {

	}

	public static void setupRobotMap() {
		RobotMap.climberReleaseServoLeft = new Servo(4);
		RobotMap.climberReleaseServoRight = new Servo(3);
	}
}
