package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

public class AutoRWall extends AutoStraightCommand {
	double aveDistI = 0.0;
	
	private static final double MIN_ENCODER_DISTANCE = 120;
	private static final double WALL_SPEED = 0.6;

	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		gyroStraight(WALL_SPEED);
	}


	@Override
	protected void initialize() {
		aveDistI = Robot.sensors.getAvgEncoderDist();
		gyroStraight(WALL_SPEED);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		double aveDistF = Robot.sensors.getAvgEncoderDist();
		return Robot.sensors.getTip() <= 5.0 &&
				aveDistF - aveDistI >= MIN_ENCODER_DISTANCE;
	}
}