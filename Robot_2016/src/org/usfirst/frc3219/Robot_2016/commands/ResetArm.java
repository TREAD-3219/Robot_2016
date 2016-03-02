package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.command.Command;

public class ResetArm extends Command {
	
	public ResetArm() {
		requires(Robot.multiTool);
	}

	@Override
	protected void initialize() {
		Robot.multiTool.disable();
		Robot.multiTool.driveArmUpDown(MultiTool.UP);
	}

	@Override
	protected void execute() {
		Robot.multiTool.driveArmUpDown(MultiTool.UP);
	}

	@Override
	protected boolean isFinished() {
		return Robot.multiTool.getUpperLimitSwitch();
	}

	@Override
	protected void end() {
		Robot.multiTool.resetEncoders();
		Robot.multiTool.driveArmUpDown(0.0);
		Robot.multiTool.driveArmHold();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
