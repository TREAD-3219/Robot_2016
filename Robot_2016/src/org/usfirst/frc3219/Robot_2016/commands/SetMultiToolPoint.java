package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.command.Command;

public class SetMultiToolPoint extends Command {
	private static final double ARM_SPEED = .6;
	double position;
	
	public SetMultiToolPoint() {
		
	}
	
	public SetMultiToolPoint(double pos) {
		position = pos;
	}

	private static final double TIMEOUT = 0.65;

	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		Robot.multiTool.driveArmUpDown(ARM_SPEED);
	}

	@Override
	protected void initialize() {
		this.setTimeout(TIMEOUT);
		Robot.multiTool.driveArmUpDown(ARM_SPEED);
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return Robot.sensors.armEncoderAngle() > 140 || this.isTimedOut();
	}
}
