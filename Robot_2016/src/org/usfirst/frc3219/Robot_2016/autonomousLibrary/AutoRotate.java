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
			isFinished = setGyroStraight(0.0, 50.0);
			break;
		case B:
			isFinished = setGyroStraight(0.0, 22.0);
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
		//boolean isAngle = false;
		/*switch (Robot.position) {
		case A:
			isAngle = angle > 214 && angle < 218;
		case B:
			isAngle = angle > 25 && angle < 35;
		case C:
			isAngle = angle > 173 && angle < 177;
		case D:
			isAngle = angle > 110 && angle < 114;
		default:
			System.out.println("Unknown position in AutoRotate.isFinished");
			break;
		}*/
		
		return isFinished || this.isTimedOut();
	}
}