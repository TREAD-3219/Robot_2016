package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualFeed extends Command {
	
	public ManualFeed() {
		requires(Robot.feedMech);
	}

	@Override
	protected void end() {
		Robot.feedMech.stopFeeder();
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		Robot.feedMech.spinFeeder(1, .3);
		this.setTimeout(5);
		
	}

	@Override
	protected void interrupted() {
		end();
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return this.isTimedOut();
	}

}
