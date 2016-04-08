package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

public class AutoMoat extends AutoStraightCommand {
	private static final double MOAT_POWER = 0.8;
	double aveDistI = 0.0;
	double aveDistF = 0.0;
	private static final double MIN_ENCODER_DISTANCE = 70.0;

	@Override
	protected void end() {
		Robot.drive.setBrakesOn();
	}

	@Override
	protected void execute() {
			gyroStraight(MOAT_POWER);
	}

	@Override
	protected void initialize() {
		this.setTimeout(4.0);
		double encoderInitialLeft = RobotMap.driveEncoderLeft.getDistance();
		double encoderInitialRight = RobotMap.driveEncoderRight.getDistance();
		aveDistI = (encoderInitialRight + encoderInitialLeft) / 2;
		gyroStraight(MOAT_POWER);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		double encoderFinalLeft = RobotMap.driveEncoderLeft.getDistance();
		double encoderFinalRight = RobotMap.driveEncoderRight.getDistance();
		aveDistF = (encoderFinalRight + encoderFinalLeft) / 2;
		//return (aveDistF - aveDistI >= MIN_ENCODER_DISTANCE);
		return this.isTimedOut();
	}
}
