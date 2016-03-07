package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoRough extends AutoStraightCommand {
	private double aveDistI = 0.0;
	private double aveDistF = 0.0;
	private static final double MIN_ENCODER_DISTANCE = 60.0;
	private static final double ROUGH_SPEED = 0.85;

	@Override
	protected void end() {
		Robot.drive.driveValues(0.0, 0.0);
		Robot.drive.setBrakesOn();
	}

	@Override
	protected void execute() {
		gyroStraight(ROUGH_SPEED);
	}

	@Override
	protected void initialize() {
		aveDistI = Robot.sensors.getAvgEncoderDist();
		gyroStraight(ROUGH_SPEED);
	}

	@Override
	protected void interrupted() {
	}

	@Override
	protected boolean isFinished() {
		aveDistF = Robot.sensors.getAvgEncoderDist();
		double deltaD = aveDistF - aveDistI;
		SmartDashboard.putNumber("AutoRough distance", deltaD);
		return (aveDistF - aveDistI >= MIN_ENCODER_DISTANCE);
	}
}
