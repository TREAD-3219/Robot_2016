package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;


import edu.wpi.first.wpilibj.Servo;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ServoController extends Command {


	private static final String QUICK_RELEASE_OVERRIDE = "quick release override";

	private static final float ZERO_POSITION = 0.0f;
	private static final float OPEN_POSITION = 180.0f;

	Servo servo4;
	Servo servo5;

	// SmartDashboard Numbers
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

	public ServoController() {
		SmartDashboard.putBoolean(QUICK_RELEASE_OVERRIDE, false);
		SmartDashboard.putNumber("PwmServo1", PwmServo4Value);
		SmartDashboard.putNumber("wait before lifting", 1);
		SmartDashboard.putNumber("drive value", 0.5);
		SmartDashboard.putNumber("wait before stoping", 2);

	}


	@Override
	protected void end() {
		this.servo4.setPosition(OPEN_POSITION);
		this.servo5.setPosition(ZERO_POSITION);
	}

	@Override
	protected void execute() {

		SmartDashboard.getBoolean(QUICK_RELEASE_OVERRIDE);

		
		
		if (quickReleaseOverride) {
			EnableClimberButtons.SafetyClimberEnable = true;

		} else { 
			
			
			if (Robot.oi.safetyStartPressed_value && Robot.oi.safetyYPressed_value
					&& EnableClimberButtons.SafetyClimberEnable) {
				// value setting
				liftWait = SmartDashboard.getNumber("wait before lifting");
				driveSpeed = SmartDashboard.getNumber("drive value");
				stopWait = SmartDashboard.getNumber("wait before stoping");
				// releasing servo quick release
				servo4.setAngle(ZERO_POSITION);
				servo5.setAngle(OPEN_POSITION);

//				System.out.print("The quick release has been triggered.");
//				// climb
//				this.setTimeout(liftWait);
//				Robot.drive.driveValues(driveSpeed, 0);
//				this.setTimeout(stopWait);
//				Robot.drive.driveValues(0, 0);

			}

		}

		/*
		 * The intended use of Servo one is for the pin pulling mechanism. The
		 * intended use of Servo two is for some other thing that is important
		 * that Z talked about
		 */
	}

	@Override
	protected void initialize() {
		this.servo4 = RobotMap.pwmServo_4;
		this.servo5 = RobotMap.pwmServo_5;

		quickReleaseOverride = SmartDashboard.getBoolean(QUICK_RELEASE_OVERRIDE);
		// SmartDashboard.putNumber("PwmServo1", PwmServo4Value);
		servo4.setPosition(ZERO_POSITION);
		servo5.setPosition(ZERO_POSITION);
		System.out.print("Servo angle Set to through the use of \"initialize\" to " + PwmServo4Value);

		//SmartDashboard.putNumber("wait before lifting", 1);
		//SmartDashboard.putNumber("drive value", 0.5);
		//SmartDashboard.putNumber("wait before stopping", 2);


	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
