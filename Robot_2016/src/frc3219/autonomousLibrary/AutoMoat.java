package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

public class AutoMoat extends AutoStraightCommand {
	double aveDistI = 0.0;
	double aveDistF = 0.0;
	private static final double MIN_ENCODER_DISTANCE = 60;

	@Override
	protected void end() {
		// RobotMap.driveLeftDrive.enableBrakeMode(true);
		// RobotMap.driveRightDrive.enableBrakeMode(true);
	}

	@Override
	protected void execute() {
		if (Robot.sensors.navx.getRawGyroY() > 15.0) {
			setGyroStraight(-0.9, 180);
		} else {
			setGyroStraight(-0.5, 180);
		}
	}

	@Override
	protected void initialize() {
		double encoderInitialLeft = RobotMap.driveEncoderLeft.getDistance();
		double encoderInitialRight = RobotMap.driveEncoderRight.getDistance();
		aveDistI = (encoderInitialRight + encoderInitialLeft) / 2;
		setGyroStraight(-0.5, 180);
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
		if (Robot.sensors.getTip() <= 3.5 && aveDistF - aveDistI >= MIN_ENCODER_DISTANCE) {
			return true;
		} else
			return false;
	}
}
