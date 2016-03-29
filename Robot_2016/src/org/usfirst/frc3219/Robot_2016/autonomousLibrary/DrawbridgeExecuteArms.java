package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DrawbridgeExecuteArms extends Command {
	
	private static final double ARM_SPEED = -0.1;

	@Override
	protected void end() {
		Robot.multiTool.driveArmUpDown(0.0);

	}

	@Override
	protected void execute() {
		Robot.multiTool.driveArmUpDown(ARM_SPEED);

	}

	@Override
	protected void initialize() {
		Robot.multiTool.driveArmUpDown(ARM_SPEED);

	}

	@Override
	protected void interrupted() {
		this.end();

	}

	@Override
	protected boolean isFinished() {
		if (Robot.sensors.armEncoderAngle() >= 95) {
			return true;
		} else {
			return false;
		}
	}

}
