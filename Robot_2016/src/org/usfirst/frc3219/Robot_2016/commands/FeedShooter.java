package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FeedShooter extends Command {
	private static final double FEEDER_SPEED = .3;
	// for moving the ball from the feeder to the shooter
	double time;
	public FeedShooter() {
		requires(Robot.shooter);
	}
	public FeedShooter(double seconds) {
		requires(Robot.feedMech);
		time = seconds;
	}

	@Override
	protected void initialize() {
		Robot.feedMech.spinFeeder(FEEDER_SPEED);
		this.setTimeout(time);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut(); 
	}

	@Override
	protected void end() {
		Robot.feedMech.stopFeeder();
	}

}
