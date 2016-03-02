package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

public class DriveOverObstacle extends AutoStraightCommand {

	double initEncoder;
	public static final double MIN_ENCODER_DISTANCE = 60;
	private static final double ROBOT_SPEED = 0.5;

	@Override
	protected void end() {
		Robot.drive.driveValues(0.0, 0.0);

	}

	@Override
	protected void execute() {

		double tip = Robot.sensors.navx.getPitch();
		if (tip >= 30) {
			// should this be gyroStraight(1.0)?
			Robot.drive.driveValues(1.0, 0.0);
		} else {
			gyroStraight(ROBOT_SPEED);
		}

	}

	@Override
	protected void initialize() {
		initEncoder = Robot.sensors.getAvgEncoderDist();
		gyroStraight(ROBOT_SPEED);

	}

	@Override
	protected void interrupted() {
		this.end();

	}

	@Override
	protected boolean isFinished() {
		double encoderDiff = Robot.sensors.getAvgEncoderDist() - initEncoder;
		if (Robot.sensors.getTip() < 5 && encoderDiff >= MIN_ENCODER_DISTANCE) {
			return true;
		}
		return false;
	}

}
