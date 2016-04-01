package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.command.Command;

public class DropArms extends Command {
	
	double P = 0.1;
	double I = 0.001;
	double D = 0.0;

	@Override
	protected void end() {
		Robot.multiTool.armSetPoint(MultiTool.DRAWBRIDGE_END);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected void initialize() {
		this.setTimeout(1.5); // Calibrate!!!
		Robot.multiTool.armSetPoint(MultiTool.DRAWBRIDGE_START);
	}

	@Override
	protected void interrupted() {
		this.end();

	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}
}
