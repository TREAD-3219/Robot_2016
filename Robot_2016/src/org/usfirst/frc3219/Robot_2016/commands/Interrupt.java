package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

// Hoover said for Overriding
public class Interrupt extends Command {
	
	public Interrupt() {
		requires(Robot.feedMech);
		requires(Robot.multiTool);
		requires(Robot.shooter);
	}

	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void interrupted() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
