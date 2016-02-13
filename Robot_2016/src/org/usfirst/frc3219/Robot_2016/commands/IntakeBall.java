package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeBall extends Command { // Starts feeder motor
	// stops either when button b is released, or limit switch is hit
	public IntakeBall() {
		requires(Robot.feedMech);
	}

	@Override
	protected void initialize() {
		Robot.feedMech.spinFeeder();

	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Robot.feedMech.getLimitSwitch();
	}

	@Override
	protected void end() {
		Robot.feedMech.stopFeeder();

	}

	@Override
	protected void interrupted() {
		end();

	}

}
