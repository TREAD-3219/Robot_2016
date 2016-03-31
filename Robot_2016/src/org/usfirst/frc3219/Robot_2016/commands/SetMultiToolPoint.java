package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.command.Command;

public class SetMultiToolPoint extends Command {
	private static final double TIMEOUT = .65;
	private static final double DRIVE_SPEED = 0.6;
	private double position;
	
	public SetMultiToolPoint() {
		position = MultiTool.SHOOT_POSITION;
	}
	
	public SetMultiToolPoint(double pos) {
		position = pos;
	}

	// Timed version
	@Override
	protected void initialize() {
		this.setTimeout(TIMEOUT);
		Robot.multiTool.driveArmUpDown(DRIVE_SPEED);
	}

	@Override
	protected void execute() {
		Robot.multiTool.driveArmUpDown(DRIVE_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut() && Robot.sensors.armEncoderAngle() >= position;
	}

	@Override
	protected void end() {
		Robot.multiTool.driveArmUpDown(0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}

/*	// Arm setpoint version
   @Override
	protected void end() {
		// no end action, just leave at setPoint
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
*/
}
