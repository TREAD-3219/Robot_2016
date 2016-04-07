package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

public class AutoRWall extends AutoStraightCommand {
	double aveDistI = 0.0;
	
	private static final double MIN_ENCODER_DISTANCE = 70.0;
	private static final double WALL_SPEED = 0.8;

	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		gyroStraight(WALL_SPEED);
	}


	@Override
	protected void initialize() {
		this.setTimeout(5.0);
		aveDistI = Robot.drive.getAvgEncoderDist();
		gyroStraight(WALL_SPEED);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		
		//double aveDistF = Robot.drive.getAvgEncoderDist();
		return this.isTimedOut();
		//return aveDistF - aveDistI >= MIN_ENCODER_DISTANCE;
	}
}