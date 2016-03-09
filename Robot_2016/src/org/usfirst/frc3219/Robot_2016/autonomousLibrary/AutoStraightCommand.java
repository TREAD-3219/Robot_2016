package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3219.Robot_2016.Robot;

public abstract class AutoStraightCommand extends Command {
	
	private static final int DEGREE_NULL_ZONE = 3;

	public void gyroStraight(double speed) {
		double angle = Robot.sensors.navx.getAngle();
		if(angle > 1 && angle < 180) {
			Robot.drive.driveValues(speed, 0.5);		
		} else if(angle < 359 && angle > 180) {
			Robot.drive.driveValues(speed, -0.5);		
		} else {
			Robot.drive.driveValues(speed, 0.0);
		}
	}
	
	public void setGyroStraight(double speed, double angle) {
		// SETUP
		double robotAngle = Robot.sensors.navx.getAngle();
		if(robotAngle > 180) { // Sets degree between 180 and -180.
			robotAngle -= 360;
		}
		double rightLim = angle + DEGREE_NULL_ZONE;
		double leftLim = angle - DEGREE_NULL_ZONE;
		if(robotAngle > rightLim) {
			Robot.drive.driveValues(0, -0.5);
		} else if(robotAngle < leftLim) {
			Robot.drive.driveValues(0, 0.5);
		} else {
			Robot.drive.driveValues(0, 0);
		}
	}
}
