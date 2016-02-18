package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.commands.AutoStraightCommand;

public class DriveOverDrawbridge extends AutoStraightCommand {
	
	double initEncoder;
	public static final double MIN_ENCODER_DISTANCE = 60;

	@Override
	protected void end() {
		Robot.drive.driveValues(0.0, 0.0);

	}

	@Override
	protected void execute() {
		gyroStraight(0.5);

	}

	@Override
	protected void initialize() {
		initEncoder = Robot.sensors.getAvgEncoderDist();
		gyroStraight(0.5);

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
