package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class EnableClimberButtons extends Command {
	private static final double TESTING_TIMEOUT = 0.5;
	private static final double CLIMBER_TIMEOUT = 135.0 - 20.0; // teleop time - high time.
	public static boolean safetyClimberEnable = false;

	@Override
	protected void end() {

		safetyClimberEnable = true;
		System.out.print("The climber can now be used!");
		// Scheduler.getInstance().add(); // TODO add the command
		/*
		 * Command climbCommand = new ServoController();
		 * Robot.oi.buttonStart.whenPressed(climbCommand);
		 * Robot.oi.buttonY.whenPressed(climbCommand);
		 */
	}

	@Override
	protected void execute() {

	}

	@Override
	protected void initialize() {
		// 1:55
		this.setTimeout(TESTING_TIMEOUT); // wait to trigger isFinished() to start end()
		safetyClimberEnable = false;
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
