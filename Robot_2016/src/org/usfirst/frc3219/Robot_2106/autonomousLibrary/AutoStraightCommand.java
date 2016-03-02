package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3219.Robot_2016.Robot;

public abstract class AutoStraightCommand extends Command {
	
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
		double rightInLim = angle + 1;
		double leftInLim = angle - 1;
		double middleLim = angle + 180;
		
		if(rightInLim > 360) {
			rightInLim = rightInLim - 360;
		}
		if(leftInLim < 0) {
			leftInLim = leftInLim + 360;
		}
		if(middleLim > 360) {
			middleLim = middleLim - 360;
		}
		
		//-----------
		
		if(robotAngle > rightInLim && robotAngle < middleLim) {
			Robot.drive.driveValues(speed, 0.5);		
		} else if(robotAngle < leftInLim && robotAngle > middleLim) {
			Robot.drive.driveValues(speed, -0.5);		
		} else {
			Robot.drive.driveValues(speed, 0.0);
		}
	}
}
