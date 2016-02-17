package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ServoController extends Command {

	
	//SmartDashboard Numbers
	double liftWait;
	double driveSpeed;
	double stopWait;
	
	
	// overall values
	int pwmAllValueStart;
	int pwmAllValueMax;
	int pwmAllValueMin;

	// TODO see if any of these values need changing
	// could be set individually
	int PwmServo5StartValue = pwmAllValueStart;
	int PwmServo5ValueMax = pwmAllValueMax - 0;
	int PwmServo5ValueMin = pwmAllValueMin - 0;
	int PwmServo5Value = PwmServo5StartValue;

	// could be set individually
	int PwmServo4StartValue = pwmAllValueStart;
	int PwmServo4ValueMax = pwmAllValueMax - 0;
	int PwmServo4ValueMin = pwmAllValueMin - 0;
	int PwmServo4Value = PwmServo4StartValue;

	boolean quickReleaseOverride = false;

	@Override
	protected void end() {

	}

	@Override
	protected void execute() {
		SmartDashboard.getBoolean("quick release overide");

		if (quickReleaseOverride == true) {
			// For manual setting of servo 1 and some safety for setting wrong
			// values ----------------------------------------
			double pwmServo4Value = SmartDashboard.getNumber("PwmServo1");
			if (pwmServo4Value >= PwmServo4ValueMin && pwmServo4Value <= PwmServo4ValueMax) {
				RobotMap.pwmServo_4.setRaw((int) pwmServo4Value);
			} else {
				if (pwmServo4Value < PwmServo4ValueMin) {
					System.out.print("The Servo cannot be set to \"" + pwmServo4Value
							+ "\"! You can only use values from 50 to 2000!");
					RobotMap.pwmServo_4.setRaw((int) PwmServo4ValueMin);
					SmartDashboard.putNumber("pwmServo_4", (int) PwmServo4ValueMin);
				} else {
					if (pwmServo4Value > PwmServo4ValueMax) {
						System.out.print("The Servo cannot be set to \"" + pwmServo4Value
								+ "\"! You can only use values from 50 to 2000!");
						RobotMap.pwmServo_4.setRaw((int) PwmServo4ValueMax);
						RobotMap.pwmServo_4.setRaw((int) PwmServo5ValueMax);
						SmartDashboard.putNumber("pwmServo_4", (int) PwmServo4ValueMax);
					} else {
						System.out
								.print("Something in ServoController Manual value setting went very, very wrong. The value that was entered is: "
										+ pwmServo4Value);
						RobotMap.pwmServo_4.setRaw((int) PwmServo4StartValue);
						RobotMap.pwmServo_5.setRaw((int) PwmServo4StartValue);
						SmartDashboard.putNumber("pwmServo_4", PwmServo4StartValue);
					}

				}

			}

			// end of Servo 1 manual ------------------------------------
			// Start of Servo 1
		} else {

			if (Robot.oi.safetyStartPressed_value && Robot.oi.safetyYPressed_value
					&& EnableClimberButtons.SafetyClimberEnable) {
				//
				liftWait = SmartDashboard.getNumber("wait before lifting");
				driveSpeed = SmartDashboard.getNumber("drive value");
				stopWait = SmartDashboard.getNumber("wait before stoping");
				
				
				RobotMap.pwmServo_4.setRaw(PwmServo4ValueMax);
				RobotMap.pwmServo_5.setRaw(PwmServo5ValueMax);
				System.out.print("The quick release has been triggered.");
				this.setTimeout(liftWait);
				Robot.drive.driveValues(driveSpeed, 0);
				this.setTimeout(stopWait);
				Robot.drive.driveValues(0, 0);
				
			}

		}

	}

	@Override
	protected void initialize() {
		SmartDashboard.putBoolean("quick release overide", false);
		SmartDashboard.putNumber("PwmServo1", PwmServo4Value);
		RobotMap.pwmServo_4.setRaw((int) PwmServo4Value);
		System.out.print("Servo angle Set to through the use of \"initialize\" to " + PwmServo4Value);
		SmartDashboard.putNumber("wait before lifting", 1);
		SmartDashboard.putNumber("drive value", 0.5);
		SmartDashboard.putNumber("wait before stoping", 2);
	
	
	}

	@Override
	protected void interrupted() {

	}

	@Override
	protected boolean isFinished() {

		return false;
	}

}
