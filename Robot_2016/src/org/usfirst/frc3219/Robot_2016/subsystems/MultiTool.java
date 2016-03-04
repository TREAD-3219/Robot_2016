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
	static final double D = -0.01;

	Talon driveRollerMotor = RobotMap.driveRollerMotorController;
	Talon driveArmMotor = RobotMap.driveMultiToolArmMotor;
	DigitalInput limitSwitchHigh = RobotMap.multiToolLimitSwitchHigh;
	DigitalInput limitSwitchLow = RobotMap.multiToolLimitSwitchLow;
	public static final String ARM_ENCODER_TAG = "Arm Encoder";

	public static int selectedTool = 0;

	// -----------
	public static final double STOW = 3.0; // close to zero degrees, but not too close.
	public static final double CHEVAL_DE_FRISE_START = 86.0;
	public static final double CHIVAL_DE_FRISE_END = 106.0;
	public static final double DRAWBRIDGE_START = 29.0;
	public static final double DRAWBRIDGE_END = 109.0;
	public static final double PORTCULLIS_START = 115.0;
	public static final double PORTCULLIS_END = 33.0;
	public static final double ROLLER_PICKUP = 104.0;
	public static final double SHOOT_POSITION = 85.0;
	public static final double NEUTRAL_POSITION = SHOOT_POSITION;
	// -----------

	
	private static final double ENCODER_MIN = 0;
	private static final double ENCODER_MAX = 120;
	public static final double RANGE = ENCODER_MAX - ENCODER_MIN;
    private static final double ENCODER_PULSE_PER_REVOLUTION = 7;
    private static final double ARM_GEAR_RATIO = 71;
    private static final double ARM_PULSE_PER_REVOLUTION = ENCODER_PULSE_PER_REVOLUTION * ARM_GEAR_RATIO;
	public static final double ARM_ENCODER_DEGREES_PER_PULSE = 360.0 / ARM_PULSE_PER_REVOLUTION;
	public static final double UP = 0.5;

	public MultiTool() {
		super(P, I, D);
		this.setInputRange(ENCODER_MIN, ENCODER_MAX);
		this.setOutputRange(-1.0, 1.0);
		this.setPercentTolerance(5);
		this.disable();
	}
	
	public void armSetPoint(double position) {
		this.setSetpoint(position);
	}

	public Boolean getUpperLimitSwitch() {
		return !limitSwitchHigh.get();
	}

	public Boolean getLowerLimitSwitch() {
		return !limitSwitchLow.get();
	}

	public void stopMotors() {
		driveArmMotor.set(0.0);
		this.disable();
	}

	public void driveRoller(double speed) {
		driveRollerMotor.set(speed);
	}

	public void driveArmUpDown(double speed) {
		driveArmMotor.set(speed);
	}

	public void driveArmHold() {
		double currentAngle = Robot.sensors.armEncoderAngle();
		this.setSetpoint(currentAngle);
		this.enable();
	}

	public double armEncoderSpeed() {
		return Robot.sensors.armEncoderSpeed();
	}

	public void resetEncoders() {
		Robot.sensors.armEncoder.reset();
	}

	public void initDefaultCommand() {
	}

	@Override
	protected double returnPIDInput() {
		double angle = Robot.sensors.armEncoderAngle();
		return angle;
	}

	@Override
	protected void usePIDOutput(double arg0) {
		if (arg0 > 0.0 && this.getUpperLimitSwitch()
				|| arg0 < 0.0 && this.getLowerLimitSwitch()) {
			driveArmMotor.pidWrite(0.0);
		} else {
			driveArmMotor.pidWrite(arg0);
		}
	}
}
