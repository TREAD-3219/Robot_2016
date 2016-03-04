package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Climber;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EnableClimberButtons extends Command {
	private static final double TESTING_TIMEOUT = 0.5;
	private static final double CLIMBER_TIMEOUT = 135.0 - 20.0; // teleop time - high time.
	
	// I don't really like the use of a public variable for this
	// public variables are generally bad design...  In this case,
	// there is only one place that SETs the variable, so it's
	// not terrible.  A better design would be to just DO whatever
	// this triggers in the end() method here.  Such as - 
	// Robot.oi.enableClimbRelease(); which does a Robot.oi.whenPressed(...)
	// There would also have to be a separate button that calls enableClimbRelease
	// which is the same button that does the equivalent of the SmartDashboard
	// enableClimberOverride - a button or something checked in teleopPeriodic.
	
	public static boolean safetyClimberEnable = false;
	
	private static final String QUICK_RELEASE_OVERRIDE = "quick release override";
	Servo servo4;
	Servo servo5;
	@Override
	protected void end() {

		safetyClimberEnable = true;
		System.out.print("The climber can now be used!");
	}

	@Override
	protected void execute() {

	}

	@Override
	protected void initialize() {
		// 1:55

		this.setTimeout(1); // wait to trigger isFinished() to start end()
		safetyClimberEnable = false;
		SmartDashboard.putBoolean(QUICK_RELEASE_OVERRIDE, false);
		servo4 = RobotMap.pwmServo_4;
		servo5 = RobotMap.pwmServo_3;
		servo4.setAngle(Climber.LEFT_SERVO_CLOSED);
		servo5.setAngle(Climber.RIGHT_SERVO_CLOSED);

	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}
}
