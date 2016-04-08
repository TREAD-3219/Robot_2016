package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

public class AutoRamparts extends AutoStraightCommand {
	private static final double RAMPART_SPEED = 1.0;
	double aveDistI = 0.0;
	double aveDistF = 0.0;
	private static final double MIN_ENCODER_DISTANCE = 70.0;
	
	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		gyroStraight(RAMPART_SPEED);
		
	}

	@Override
	protected void initialize() {
		aveDistI = Robot.drive.getAvgEncoderDist();
		this.setTimeout(3.0);
		gyroStraight(RAMPART_SPEED);
		Robot.drive.setBrakesOff();
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
//		aveDistF = Robot.drive.getAvgEncoderDist();
//		if (aveDistF - aveDistI >= MIN_ENCODER_DISTANCE) {
//			return true;
//		} else
//		return false;
		return this.isTimedOut();
	}
}
