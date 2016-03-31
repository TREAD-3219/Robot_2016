package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoRotate extends AutoStraightCommand {
	
	public boolean isFinished = false;

	@Override
	protected void end() {
		SmartDashboard.putBoolean("AutoRotateFinished", true);
		Robot.drive.driveValues(0.0, 0.0);
	}

	@Override
	protected void execute() {
		this.setDefenseDirection();
	}

	@Override
	protected void initialize() {
		isFinished = false;
		SmartDashboard.putBoolean("AutoRotateFinished", false);
		System.out.println("AutoRotate.initialize");
		this.setTimeout(2);
		setDefenseDirection();
	}

	protected void setDefenseDirection() {
		switch (Robot.position) {
		case A:
			isFinished = setGyroStraight(0.0, 38.0); // Needs testing.
			break;
		case B:
			isFinished = setGyroStraight(0.0, 22.0);
			break;
		case C:
			isFinished = setGyroStraight(0.0, 354.0); // Needs testing. -6
			break;
		case D:
			isFinished = setGyroStraight(0.0, 338.0); // Needs testing. -22
			break;
		default:
			System.out.println("Unknown case in setDefenseDirection");
			isFinished = true; // Bad code if it gets here!
			break;
		}
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		double angle = Robot.sensors.navx.getAngle();
		return isFinished || this.isTimedOut();
	}
}