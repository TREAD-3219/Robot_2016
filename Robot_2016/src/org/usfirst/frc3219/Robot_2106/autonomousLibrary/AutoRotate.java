package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

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
		System.out.println("init");
		this.setTimeout(2);
		setDefenseDirection();
	}

	void setDefenseDirection() {
		switch (Robot.position) {
		case A: 
			setGyroStraight(0.0, 216.0);
			break;
		case B:
			setGyroStraight(0.0, 200.0);
			break;
		case C:
			setGyroStraight(0.0, 175.0);
			break;
		case D:
			setGyroStraight(0.0, 112.0);
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
		switch (Robot.position) {
		case A: 
			return angle > 214 && angle < 218;
		case B:
			return angle > 198 && angle < 202;
		case C:
			return angle > 173 && angle < 177;
		case D:
			return angle > 110 && angle < 114;
		}
			return this.isTimedOut();
			
	}
}