package org.usfirst.frc3219.Robot_2016.commands;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTimeout extends Command {
	private double timeout;
	
	public AutoTimeout() {
		timeout = 10.0;
	}
	
	public AutoTimeout(double timeoutInput) {
		timeout = timeoutInput;
	}
	
	@Override
	protected void initialize() {
		this.setTimeout(timeout);
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	protected void interrupted() {
		
	}

	@Override
	protected boolean isFinished() {
		boolean isTimedOut = this.isTimedOut();
		return isTimedOut;
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

}
