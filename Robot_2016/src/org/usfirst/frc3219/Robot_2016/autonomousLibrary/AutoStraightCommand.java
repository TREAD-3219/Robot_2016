package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3219.Robot_2016.Robot;

public abstract class AutoStraightCommand extends Command {

	private static final int DEGREE_NULL_ZONE = 10;

	public void gyroStraight(double speed) {
		double angle = Robot.sensors.navx.getAngle();
		if (angle > 1 && angle < 180) {
			Robot.drive.driveValues(speed, 0.5);
		} else if (angle < 359 && angle > 180) {
			Robot.drive.driveValues(speed, -0.5);
		} else {
			Robot.drive.driveValues(speed, 0.0);
		}
	}

	public boolean setGyroStraight(double speed, double angle) {
		// SETUP
		/*
		 * double robotAngle = Robot.sensors.navx.getAngle(); angle -= 180;
		 * 
		 * double rightLim = angle + DEGREE_NULL_ZONE; double leftLim = angle -
		 * DEGREE_NULL_ZONE; if (robotAngle > 180) { // Sets degree between 180
		 * and -180. robotAngle -= 360; }
		 * 
		 * if (robotAngle > rightLim) { Robot.drive.driveValues(0, -0.45); }
		 * else if (robotAngle < leftLim) { Robot.drive.driveValues(0, 0.45); }
		 * else { Robot.drive.driveValues(0, 0); }
		 */
		double robotAngle = Robot.sensors.navx.getAngle();
		double rightLim = angle + DEGREE_NULL_ZONE;
		double leftLim = angle - DEGREE_NULL_ZONE;
		if(robotAngle < rightLim && robotAngle > leftLim) {
			return true;
		}
		if(angle > 180) {
			Robot.drive.driveValues(0, 0.5);
		} else {
			Robot.drive.driveValues(0, -0.5);
		}
		return false;
	}
}
