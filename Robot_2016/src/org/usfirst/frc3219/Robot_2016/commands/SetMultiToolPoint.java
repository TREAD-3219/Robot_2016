package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetMultiToolPoint extends Command {
	String POSITION;
	
	public SetMultiToolPoint(String PositionChoice) {
		POSITION = PositionChoice;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		Robot.multiTool.armSetPoint(POSITION);
		this.setTimeout(2);
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

}
