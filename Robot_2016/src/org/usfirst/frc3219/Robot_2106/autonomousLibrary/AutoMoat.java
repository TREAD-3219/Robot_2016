package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

public class AutoMoat extends AutoStraightCommand {
	double aveDistI = 0.0;
	double aveDistF = 0.0;
	private static final double MIN_ENCODER_DISTANCE = 150;

	@Override
	protected void end() {
		Robot.drive.setBrakesOn();
	}

	@Override
	protected void execute() {
			setGyroStraight(0.8, 0);
	}

	@Override
	protected void initialize() {
		double encoderInitialLeft = RobotMap.driveEncoderLeft.getDistance();
		double encoderInitialRight = RobotMap.driveEncoderRight.getDistance();
		aveDistI = (encoderInitialRight + encoderInitialLeft) / 2;
		setGyroStraight(0.8, 0);
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
		return (aveDistF - aveDistI >= MIN_ENCODER_DISTANCE);
	}
}
