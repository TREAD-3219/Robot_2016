package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FeedShooter extends Command {
	//need to feed shooter and check if limit switch is activated
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
		Robot.feedMech.spinFeeder();
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
			//return if timed out or if limit switch is clicked
		return this.isTimedOut();
	}
	
	@Override
	protected void end() {
		Robot.feedMech.stopFeeder();
		
	}

}
