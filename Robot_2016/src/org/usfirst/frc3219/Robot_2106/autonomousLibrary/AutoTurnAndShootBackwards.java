package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

public class AutoTurnAndShootBackwards extends AutoStraightCommand {

	// ONLY FOR THE ROBOT WHEN STARTED IN REVERSE.
	// Needed because the gyroscope is set 180 off of the original AutoTurnAndShoot command.
	
	String whichPosition;
	double initEncoderDist;
	double currentEncoderDist;

	@Override
	protected void end() {
		Robot.drive.driveValues(0.0, 0.0);
	}

	@Override
	protected void execute() {
		currentEncoderDist = Robot.sensors.aveDistEncoders() - initEncoderDist;
		if (whichPosition.equalsIgnoreCase("position a")) {
			setGyroStraight(0.45, 216.0);
		} else if (whichPosition.equalsIgnoreCase("position b")) {
			setGyroStraight(0.45, 200.0);
		} else if (whichPosition.equalsIgnoreCase("position c")) {
			setGyroStraight(0.45, 175.0);
		} else if (whichPosition.equalsIgnoreCase("position d")) {
			setGyroStraight(0.45, 112.0);
		}
	}

	@Override
	protected void initialize() {
		this.setTimeout(2.5);
		initEncoderDist = Robot.sensors.aveDistEncoders();
		whichPosition = (String) Robot.oi.autoDefenseChooser.getSelected();
		if (whichPosition.equalsIgnoreCase("position a")) {
			setGyroStraight(0.45, 216.0);
		} else if (whichPosition.equalsIgnoreCase("position b")) {
			setGyroStraight(0.45, 200.0);
		} else if (whichPosition.equalsIgnoreCase("position c")) {
			setGyroStraight(0.45, 175.0);
		} else if (whichPosition.equalsIgnoreCase("position d")) {
			setGyroStraight(0.45, 112.0);
		}
	}

	@Override
	protected void interrupted() {
		this.end();

	}

	@Override
	protected boolean isFinished() {
		if (this.isTimedOut() || currentEncoderDist > 70) {
			return true;
		} else {
			return false;
		}
	}
}
