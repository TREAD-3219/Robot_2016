package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ServoController_SafetyStartPressed extends Command {

	// PRESSED PRESSED PRESSED PRESSED
	// START START START START START
	@Override
	protected void end() {
		Robot.oi.safetyStartPressed_value = false;
	}

	@Override
	protected void execute() {
//    	if(Robot.oi.safetyYPressed_value && Robot.oi.safetyStartPressed_value) {
//    		System.out.println("Both buttons pressed for ServoController.");
//    		Scheduler.getInstance().add(new ServoController());
//    	}
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
