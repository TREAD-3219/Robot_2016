package org.usfirst.frc3219.Robot_2016.commands;


import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ServoController_SafetyStartPressed extends Command {
	
	//PRESSED PRESSED PRESSED PRESSED 
	//START START START START START
	@Override
	protected void end() {
		Robot.oi.safetyStartPressed_value = false;
		
	}

	@Override
	protected void execute() {

	}

	@Override
	protected void initialize() {
	
		Robot.oi.safetyStartPressed_value = true;
		
		
		}
	

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
