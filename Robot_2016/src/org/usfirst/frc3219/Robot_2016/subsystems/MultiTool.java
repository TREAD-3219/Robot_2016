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

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MultiTool extends Subsystem {

	public static final String DRIVE_ARM_SPEED_TAG = "Drive Arm Speed";

	Talon driveRollerMotor = RobotMap.driveRollerMotorController;
	Talon driveArmMotor = RobotMap.driveMultiToolArmMotor;
	DigitalInput limitSwitchHigh = RobotMap.multiToolLimitSwitchHigh;
	DigitalInput limitSwitchLow = RobotMap.multiToolLimitSwitchLow;
	public static final String ARM_ENCODER_TAG = "Arm Encoder";
	public static final String ARM_P = "Arm P";
	public static final String ARM_I = "Arm I";
	public static final String ARM_D = "Arm D";
	public static final String ARM_LOWER_LIMIT_TAG = "Arm lower limit";
	public static final String ARM_UPPER_LIMIT_TAG = "Arm upper limit";
	public static final String ARM_POWER_SETTING_TAG = "Arm Power Setting";
	public static final String ARM_SET_POINT = "Arm Set Point";

	public static int selectedTool = 0;

	// -----------
	public static final double STOW = 3.0; // close to zero degrees, but not too
											// close.
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

	private static final double MOTOR_POWER_MAX_DOWN = 0.5;
	private static final double MOTOR_POWER_MAX_UP = -1.0;
	private static final double ARM_TOLERANCE_PERCENT = 5.0;
	private static final double ENCODER_MIN = 0;
	private static final double ENCODER_MAX = 110;
	public static final double RANGE = ENCODER_MAX - ENCODER_MIN;
	private static final double ENCODER_PULSE_PER_REVOLUTION = 7.0; // not sure about this one...
	private static final double ARM_GEAR_RATIO = 188;
	private static final double ARM_PULSE_PER_REVOLUTION = ENCODER_PULSE_PER_REVOLUTION * ARM_GEAR_RATIO;

	public static final double ARM_ENCODER_DEGREES_PER_PULSE = 360.0 / ARM_PULSE_PER_REVOLUTION;
	public static final double UP_POWER = -0.5; // Must be negative
	
	
	public MultiTool() {
		this.resetEncoders();
	}

	public void armSetPoint(double position) {
	}

	public Boolean getUpperLimitSwitch() {
		return !limitSwitchHigh.get();
	}

	public Boolean getLowerLimitSwitch() {
		return !limitSwitchLow.get();
	}

	public void stopMotors() {
		driveArmMotor.set(0.0);
		SmartDashboard.putNumber(DRIVE_ARM_SPEED_TAG, 0.0);
	}

	public void driveRoller(double speed) {
		driveRollerMotor.set(speed); // Must be negative
	}

	public void driveArmUpDown(double power) {
		// positive power is DOWN
		if (power > 0.0 && this.getUpperLimitSwitch() || power < 0.0 && this.getLowerLimitSwitch()) {
			driveArmMotor.set(0.0);
			if (this.getUpperLimitSwitch()) {
				this.resetEncoders();
			}
			SmartDashboard.putNumber(DRIVE_ARM_SPEED_TAG, 0.0);
		} else {
			driveArmMotor.set(power);
			SmartDashboard.putNumber(DRIVE_ARM_SPEED_TAG, power);
		}
	}

	public void driveArmHold() {
	}

	public double armEncoderSpeed() {
		return Robot.sensors.armEncoderSpeed();
	}

	public void resetEncoders() {
		Robot.sensors.armEncoder.reset();
	}
	
	public void initDefaultCommand() {
	}

	public static void setupRobotMap() {
		RobotMap.driveRollerMotorController = new Talon(1);
		RobotMap.driveMultiToolArmMotor = new Talon(2);
		RobotMap.multiToolLimitSwitchHigh = new DigitalInput(6);
		RobotMap.multiToolLimitSwitchLow = new DigitalInput(7);
		
		RobotMap.sensorsArmEncoder = new Encoder(4, 5, false, EncodingType.k4X);
		RobotMap.sensorsArmEncoder.setDistancePerPulse(ARM_ENCODER_DEGREES_PER_PULSE);
		RobotMap.sensorsArmEncoder.setPIDSourceType(PIDSourceType.kRate);
	}
}
