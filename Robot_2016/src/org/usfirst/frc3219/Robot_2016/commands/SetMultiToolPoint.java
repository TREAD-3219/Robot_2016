package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.command.Command;

public class SetMultiToolPoint extends Command {
	private static final double TIMEOUT = .65;
	private double position;
	
	public SetMultiToolPoint() {
		position = 85.0;
	}
	
	public SetMultiToolPoint(double pos) {
		position = pos;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
	}

	@Override
	protected void initialize() {
		this.setTimeout(TIMEOUT);
		Robot.multiTool.enable();
		Robot.multiTool.armSetPoint(position);
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return Robot.multiTool.onTarget() || this.isTimedOut();
	}
}
