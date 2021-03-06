package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoChevalDeFrise extends AutoStraightCommand {
	double aveDistI = 0.0;
	double aveDistF = 0.0;
	private static final double MIN_ENCODER_DISTANCE = 70.0; // NOT CALIBRATED

	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		// this seems kind of fast for this...
		gyroStraight(0.85);
	}

	@Override
	protected void initialize() {
		Robot.drive.setBrakesOn();
		aveDistI = Robot.drive.getAvgEncoderDist();
		gyroStraight(0.85);
	}

	@Override
	protected void interrupted() {

	}

	@Override
	protected boolean isFinished() {
		aveDistF = Robot.drive.getAvgEncoderDist();
		if(aveDistF - aveDistI >= MIN_ENCODER_DISTANCE) {
			return true;
		} else
		return false;
	}
}
