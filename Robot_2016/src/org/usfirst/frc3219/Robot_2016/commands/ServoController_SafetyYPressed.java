package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ServoController_SafetyYPressed extends Command {
	public static double safetyYPressed_value;

	// PRESS PRESS PRESS PRESS
	// Y Y Y Y Y Y Y Y
	@Override
	protected void end() {
		Robot.oi.safetyYPressed_value = true;
	}

	@Override
	protected void execute() {

	}

	@Override
	protected void initialize() {
		Robot.oi.safetyYPressed_value = true;

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
