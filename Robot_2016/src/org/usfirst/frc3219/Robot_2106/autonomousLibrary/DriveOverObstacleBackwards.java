package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;


/**
 *
 */
public class DriveOverObstacleBackwards extends AutoStraightCommand {

	double initEncoder;
	public static final double MIN_ENCODER_DISTANCE = 60;
	private static final double ROBOT_BACK_SPEED = -0.5;

	@Override
	protected void end() {
		Robot.drive.setBrakesOn();

	}

	@Override
	protected void execute() {
		gyroStraight(ROBOT_BACK_SPEED);

	}

	@Override
	protected void initialize() {
		initEncoder = Robot.sensors.getAvgEncoderDist();
		gyroStraight(ROBOT_BACK_SPEED);

	}

	@Override
	protected void interrupted() {
		this.end();

	}

	@Override
	protected boolean isFinished() {
		double encoderDiff = Robot.sensors.getAvgEncoderDist() - initEncoder;
		if(Robot.sensors.getTip() < 5 && encoderDiff >= MIN_ENCODER_DISTANCE) {
			return true;
		}
		return false;
	}
}
