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

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class MultiTool extends PIDSubsystem {

	static final double P = 0.01;
	static final double I = 0.001;
	static final double D = 0.0;

	Talon driveRollerMotor = RobotMap.driveRollerMotorController;
	Talon driveArmMotor = RobotMap.driveMultiToolArmMotor;
	DigitalInput limitSwitchHigh = RobotMap.multiToolLimitSwitchHigh;
	DigitalInput limitSwitchLow = RobotMap.multiToolLimitSwitchLow;
	public static final String ARM_ENCODER_TAG = "Arm Encoder";

	public static final String PORTCULLIS_START_TAG = "PORTCULLIS_START";
	public static final String DRAWBRIDGE_START_TAG = "DRAWBRIDGE_START";
	public static final String STOW_TAG = "STOW";
	public static final String ROLLER_PICKUP_TAG = "ROLLER_PICKUP";
	public static final String CHEVAL_DE_FRISE_START_TAG = "CHIVAL_DE_FRISE_START";
	public static final String PORTCULLIS_END_TAG = "PORTCULLIS_END";
	public static final String DRAWBRIDGE_END_TAG = "DRAWBRIDGE_END";
	public static final String CHIVAL_DE_FRISE_END_TAG = "CHIVAL_DE_FRISE_END";
	public static int selectedTool = 0;

	// -----------
	public static final double STOW = 1.0; // close to zero degrees.
	public static final double DRAWBRIDGE_START = 29.0;
	public static final double PORTCULLIS_START = 115.0;
	public static final double ROLLER_PICKUP = 104.0;
	public static final double CHEVAL_DE_FRISE_START = 86.0;
	// -----------

	public static final double PORTCULLIS_END = 33.0;
	public static final double DRAWBRIDGE_END = 109.0;
	public static final double CHIVAL_DE_FRISE_END = 106.0;
	private static final double ENCODER_MIN = 0;
	private static final double ENCODER_MAX = 120;
	public static final double RANGE = ENCODER_MAX - ENCODER_MIN;

	public MultiTool() {
		super(P, I, D);
		this.setInputRange(ENCODER_MIN, ENCODER_MAX);
		this.setOutputRange(0.0, 1.0);
	}

	public void armSetPoint(String position) {
		if (position.equalsIgnoreCase(STOW_TAG)) {
			this.setSetpoint(STOW);
		} else if (position.equals(DRAWBRIDGE_START_TAG)) {
			this.setSetpoint(DRAWBRIDGE_START);
		} else if (position.equalsIgnoreCase(PORTCULLIS_START_TAG)) {
			this.setSetpoint(PORTCULLIS_START);
		} else if (position.equalsIgnoreCase(ROLLER_PICKUP_TAG)) {
			this.setSetpoint(ROLLER_PICKUP);
		} else if (position.equalsIgnoreCase(CHEVAL_DE_FRISE_START_TAG)) {
			this.setSetpoint(CHEVAL_DE_FRISE_START);
		} else if (position.equalsIgnoreCase(CHIVAL_DE_FRISE_END_TAG)) {
			this.setSetpoint(CHIVAL_DE_FRISE_END);
		} else if (position.equalsIgnoreCase(PORTCULLIS_END_TAG)) {
			this.setSetpoint(PORTCULLIS_END);
		} else if (position.equalsIgnoreCase(DRAWBRIDGE_END_TAG)) {
			this.setSetpoint(DRAWBRIDGE_END);
		} else {
			System.out.println("Error: Bad armSetPoint String parameter.");
		}
	}

	public Boolean readUpperMultiToolLimitSwitch() {
		return !limitSwitchHigh.get();
	}

	public Boolean readLowerMultiToolLimitSwitch() {
		return !limitSwitchLow.get();
	}

	public void stopMotors() {
		driveArmMotor.set(0.0);
		this.disable();
	}

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public void driveRoller(double speed) {
		driveRollerMotor.set(speed);
	}

	public void driveArmUpDown(double speed) {
		double direction = Math.signum(speed);
		this.setSetpointRelative(direction);
	}

	public void driveArmHold() {
		double currentAngle = Robot.sensors.armEncoderAngle();
		this.setSetpoint(currentAngle);
		this.enable();
	}

	public void initDefaultCommand() {// assuming radius is 26
	}

	// Math.sqrt(625 - Math.sin(sensors.armEncoderRaw())/25)

	@Override
	protected double returnPIDInput() {
		double angle = Robot.sensors.armEncoderAngle();
		return angle;
	}

	@Override
	protected void usePIDOutput(double arg0) {
		if (arg0 > 0.0 && this.readUpperMultiToolLimitSwitch() || arg0 < 0.0 && this.readLowerMultiToolLimitSwitch()) {
			driveArmMotor.pidWrite(0.0);
		} else {
			driveArmMotor.pidWrite(arg0);
		}
	}
}
