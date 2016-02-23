package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FeedShooter extends Command {
	// for moving the ball from the feeder to the shooter
	double time;
	public FeedShooter() {
		requires(Robot.shooter);
	}
	public FeedShooter(double seconds) {
		requires(Robot.shooter);
		time = seconds;
	}

	@Override
	protected void initialize() {
		Robot.feedMech.spinFeeder(1);
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
