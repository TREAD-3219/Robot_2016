package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

public class AutoRotate extends AutoStraightCommand {

	@Override
	protected void end() {
		Robot.drive.driveValues(0.0, 0.0);
	}

	@Override
	protected void execute() {
		this.setDefenseDirection();
	}

	@Override
	protected void initialize() {
		System.out.println("AutoRotate.initialize");
		this.setTimeout(2);
		setDefenseDirection();
	}

	protected void setDefenseDirection() {
		switch (Robot.position) {
		case A:
			setGyroStraight(0.0, 30.0);
			break;
		case B:
			setGyroStraight(0.0, 10.0);
			break;
		case C:
			setGyroStraight(0.0, -8.0);
			break;
		case D:
			setGyroStraight(0.0, -27.0);
			break;
		default:
			System.out.println("Unknown case in setDefenseDirection");
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
		boolean isAngle = false;
		switch (Robot.position) {
		case A:
			isAngle = angle > 214 && angle < 218;
		case B:
			isAngle = angle > 198 && angle < 202;
		case C:
			isAngle = angle > 173 && angle < 177;
		case D:
			isAngle = angle > 110 && angle < 114;
		default:
			System.out.println("Unknown position in AutoRotate.isFinished");
			break;
		}
		
		return isAngle || this.isTimedOut();
	}
}