package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoChivalDeFrise extends AutoStraightCommand {
	double aveDistI = 0.0;
	double aveDistF = 0.0;
	private static final double MIN_ENCODER_DISTANCE = 60; // NOT CALIBRATED

	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		gyroStraight(0.85);
	}

	@Override
	protected void initialize() {
		aveDistI = Robot.sensors.getAvgEncoderDist();
		gyroStraight(0.85);

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		aveDistF = Robot.sensors.getAvgEncoderDist();
		if(Robot.sensors.getTip() <= 5 && aveDistF - aveDistI >= MIN_ENCODER_DISTANCE) {
			return true;
		} else
		return false;
	}
}
