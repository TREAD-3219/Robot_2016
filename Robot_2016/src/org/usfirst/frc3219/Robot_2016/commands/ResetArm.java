package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.command.Command;

public class ResetArm extends Command {
	private double startPosition;
	
	public ResetArm() {
		// just take over - can't require multiTool
		// because MultiToolMover is not interruptible
	}

	@Override
	protected void initialize() {
		startPosition = Robot.multiTool.getPosition();
		Robot.multiTool.disable();
		Robot.multiTool.driveArmUpDown(MultiTool.UP_POWER);
	}

	@Override
	protected void execute() {
		Robot.multiTool.driveArmUpDown(MultiTool.UP_POWER);
	}

	@Override
	protected boolean isFinished() {
		return Robot.multiTool.getUpperLimitSwitch();
	}

	@Override
	protected void end() {
		// get the position readout before resetting
		double currentPosition = Robot.multiTool.getPosition();
		Robot.multiTool.resetEncoders();
		Robot.multiTool.driveArmUpDown(0.0);
		double correctedPosition = startPosition - currentPosition;
		Robot.multiTool.armSetPoint(correctedPosition);
		Robot.multiTool.enable();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
