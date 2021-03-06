package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RunFeeder extends Command { //Spins shooter while X is pressed

	private static final double FEEDER_SPEED = .3;

	@Override
	protected void initialize() {
		Robot.feedMech.spinFeeder(FEEDER_SPEED);
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		end();
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void end() {
		Robot.feedMech.stopFeeder();
		
	}

}
