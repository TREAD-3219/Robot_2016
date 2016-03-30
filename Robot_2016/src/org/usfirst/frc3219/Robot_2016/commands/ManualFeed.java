package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualFeed extends Command {
	
	private static final int TIMEOUT = 5;
	private static final double FEEDER_SPEED = .3;

	public ManualFeed() {
		requires(Robot.feedMech);
	}

	@Override
	protected void end() {
		Robot.feedMech.stopFeeder();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected void initialize() {
		Robot.feedMech.spinFeeder(FEEDER_SPEED);
		this.setTimeout(TIMEOUT);
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
