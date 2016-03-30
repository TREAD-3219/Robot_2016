package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

public class AutoTurnTowardsGoal extends AutoStraightCommand {

	String whichPosition;
	double initEncoderDist;
	double currentEncoderDist;
	double angleToTurnTo = 0;

	@Override
	protected void end() {
		Robot.drive.setBrakesOn();
		Robot.drive.driveValues(0.0, 0.0);
	}

	@Override
	protected void execute() {
		currentEncoderDist = Robot.drive.getAvgEncoderDist() - initEncoderDist;
		setGyroStraight(0.45, angleToTurnTo);
	}

	@Override
	protected void initialize() {
		this.setTimeout(2.5);
		initEncoderDist = Robot.drive.getAvgEncoderDist();
		switch (Robot.position) {
		case A:
			angleToTurnTo = 36;
			break;
		case B:
			angleToTurnTo = 20;
			break;
		case C:
			angleToTurnTo = 355;
			break;
		case D:
			angleToTurnTo = 300;
			break;
			
			default:
				System.out.println("Unknown Position in AutoTurnAndShoot: " + Robot.position.name());
				break;
		}
		setGyroStraight(0.45, angleToTurnTo);
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
