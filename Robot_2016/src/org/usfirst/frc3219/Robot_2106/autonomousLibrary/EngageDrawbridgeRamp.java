package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

public class EngageDrawbridgeRamp extends AutoStraightCommand {

	private static final double RAMP_SPEED = 0.6;
	
	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		gyroStraight(RAMP_SPEED);
		if(!(Robot.sensors.armEncoderDistance() < 1)) {
			Robot.multiTool.driveArmUpDown(0.2); // Arbitrary guess!
		}
	}

	@Override
	protected void initialize() {
		gyroStraight(RAMP_SPEED);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		if(Robot.sensors.getTip() >= 5.0) {
			return true;
		} else
		return false;
	}
}
