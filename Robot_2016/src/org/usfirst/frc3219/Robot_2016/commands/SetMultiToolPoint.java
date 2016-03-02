package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.command.Command;

public class SetMultiToolPoint extends Command {
	double position;
	
	public SetMultiToolPoint(double pos) {
		position = pos;
	}

	private static final int TIMEOUT = 2;

	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
	}

	@Override
	protected void initialize() {
		this.setTimeout(TIMEOUT);
		Robot.multiTool.armSetPoint(position);
	}

	@Override
	protected void interrupted() {
	}

	@Override
	protected boolean isFinished() {
		return Robot.multiTool.getPIDController().onTarget() || this.isTimedOut();
	}
}
