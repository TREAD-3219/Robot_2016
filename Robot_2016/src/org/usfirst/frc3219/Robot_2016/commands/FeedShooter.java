package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

// which command will we use?  This one or IntakeBall?
// get rid of the one we are NOT using
public class FeedShooter extends Command {
	// need to feed shooter and check if limit switch is activated
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
		// return if timed out or if limit switch is clicked
		return this.isTimedOut(); // how about that limit switch?
	}

	@Override
	protected void end() {
		Robot.feedMech.stopFeeder();
	}

}
