package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoRough extends AutoStraightCommand {
	double aveDistI = 0.0;
	double aveDistF = 0.0;
	public static final String AUTO_ROUGH_FINISH_TAG = "AutoRoughFinish";
	private static final double MIN_ENCODER_DISTANCE = 35.0; // CHANGED FOR MULE
	private static final double ROUGH_SPEED = 0.85;

	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		gyroStraight(ROUGH_SPEED);
	}

	@Override
	protected void initialize() {
		SmartDashboard.putBoolean(AUTO_ROUGH_FINISH_TAG, false);
		this.setTimeout(2);
		aveDistI = Robot.drive.getAvgEncoderDist();
		gyroStraight(ROUGH_SPEED);
	}
	
	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		aveDistF = Robot.drive.getAvgEncoderDist();
		boolean stop = (aveDistF - aveDistI >= MIN_ENCODER_DISTANCE);
		SmartDashboard.putBoolean(AUTO_ROUGH_FINISH_TAG, stop);
		return stop || this.isTimedOut();
	}
}
