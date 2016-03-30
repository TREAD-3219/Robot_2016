package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

public class EngageDrawbridge extends AutoStraightCommand {
	double initAccelX;
	double initAccelY;
	public static final double JERK_THRESHOLD = 0.5; // Not calibrated.
	private static final double DRAWBRIDGE_SPEED = 0.45;
	
	@Override
	protected void end() {
		Robot.drive.setBrakesOn();
	}

	@Override
	protected void execute() {
		gyroStraight(DRAWBRIDGE_SPEED);

	}

	@Override
	protected void initialize() {
		gyroStraight(DRAWBRIDGE_SPEED);

	}

	@Override
	protected void interrupted() {
		end();

	}

	@Override
	protected boolean isFinished() {
		double finAccelX = Robot.sensors.navx.getWorldLinearAccelX();
		double finAccelY = Robot.sensors.navx.getWorldLinearAccelY();
		double xJerk = Math.abs(finAccelX - initAccelX);
		double yJerk = Math.abs(finAccelY - initAccelY);
		if(xJerk > JERK_THRESHOLD || yJerk > JERK_THRESHOLD) {
			return true;
		} else {
			return false;
		}
	}

}
