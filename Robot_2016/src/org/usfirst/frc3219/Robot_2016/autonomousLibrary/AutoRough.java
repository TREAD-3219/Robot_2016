package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoRough extends AutoStraightCommand {
	double aveDistI = 0.0;
	double aveDistF = 0.0;
	private static final double MIN_ENCODER_DISTANCE = 27.0; // CHANGED FOR MULE
	private static final double ROUGH_SPEED = 0.85;

	@Override
	protected void end() {
		aveDistF = Robot.sensors.getAvgEncoderDist();
		SmartDashboard.putBoolean("AutoRoughFinish", true);
	}

	@Override
	protected void execute() {
		gyroStraight(ROUGH_SPEED);
	}

	@Override
	protected void initialize() {
		SmartDashboard.putBoolean("AutoRoughFinished", false);
		this.setTimeout(2);
		aveDistI = Robot.sensors.getAvgEncoderDist();
		gyroStraight(ROUGH_SPEED);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		aveDistF = Robot.sensors.getAvgEncoderDist();
		boolean stop = (aveDistF - aveDistI >= MIN_ENCODER_DISTANCE);
		SmartDashboard.putBoolean("AutoRoughFinished", stop);
		return stop || this.isTimedOut();
	}
}