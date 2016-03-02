package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

public class AutoRotate extends AutoStraightCommand {
	String whichPosition;

	@Override
	protected void end() {
		Robot.drive.driveValues(0.0, 0.0);
	}

	@Override
	protected void execute() {
		if (whichPosition.equalsIgnoreCase("position a")) {
			setGyroStraight(0.0, 216.0);
		} else if (whichPosition.equalsIgnoreCase("position b")) {
			setGyroStraight(0.0, 200.0);
		} else if (whichPosition.equalsIgnoreCase("position c")) {
			setGyroStraight(0.0, 175.0);
		} else if (whichPosition.equalsIgnoreCase("position d")) {
			setGyroStraight(0.0, 112.0);
		}
	}

	@Override
	protected void initialize() {
		whichPosition = (String) Robot.oi.autoDefenseChooser.getSelected();
		if (whichPosition.equalsIgnoreCase("position a")) {
			setGyroStraight(0.0, 216.0);
		} else if (whichPosition.equalsIgnoreCase("position b")) {
			setGyroStraight(0.0, 200.0);
		} else if (whichPosition.equalsIgnoreCase("position c")) {
			setGyroStraight(0.0, 175.0);
		} else if (whichPosition.equalsIgnoreCase("position d")) {
			setGyroStraight(0.0, 112.0);
		}
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		double angle = Robot.sensors.navx.getAngle();
		if (whichPosition.equalsIgnoreCase("position a")) {
			if (angle > 214 && angle < 218) {
				return true;
			}
		} else if (whichPosition.equalsIgnoreCase("position b")) {
			if (angle > 198 && angle < 202) {
				return true;
			}
		} else if (whichPosition.equalsIgnoreCase("position c")) {
			if (angle > 173 && angle < 177) {
				return true;
			}
		} else if (whichPosition.equalsIgnoreCase("position d")) {
			if (angle > 110 && angle < 114) {
				return true;
			}
		}
			return false;
	}
}