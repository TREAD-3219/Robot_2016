package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ReverseCommand extends Command{

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.drive.reverse();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}
}