package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class EnableClimberButtons extends Command {
	public static boolean SafetyClimberEnable = false;

	@Override
	protected void end() {

		SafetyClimberEnable = true;
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
		this.setTimeout(1); // wait to trigger isFinished() to start end()
		SafetyClimberEnable = false;
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
