package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// A class for Deductive Reckoning navigation
public class DedReckoningChecks extends NeverFinishCommand {

	private static final String DEAD_REC_AREA = "Dead Rec Area";
	private static final String DEAD_REC_ANGLE = "Dead Rec Angle";
	private static final String DEAD_REC_Y = "Dead Rec Y";
	private static final String DEAD_REC_X = "Dead Rec X";

	@Override
	protected void initialize() {
		computeCurrentPosition();
	}

	@Override
	protected void execute() {
		computeCurrentPosition();
	}

	void computeCurrentPosition() {
		double angleIsOffBy = Robot.sensors.getAngle() - Robot.navigation.getDedRecAngle();
		// Angle checks
		if (Math.abs(angleIsOffBy) >= 1.0) {
			Robot.navigation.dedRecTurned(angleIsOffBy);
		}
		
		Robot.navigation.checkAttitude(); // am I happy?
		
		// OuterWorks Checks
		if (Robot.navigation.inOuterWorks()) {
			if (Robot.navigation.getDedRecArea().equals("Enemy Outer Works")) {
				Robot.navigation.dedRecX = 410;

			} else if (Robot.navigation.getDedRecArea().equals("Friendly Outer Works")) {
				Robot.navigation.dedRecX = 190;
			}
		}
		
		SmartDashboard.putNumber(DEAD_REC_X, Robot.navigation.getDedRecX());
		SmartDashboard.putNumber(DEAD_REC_Y, Robot.navigation.getDedRecY());
		SmartDashboard.putNumber(DEAD_REC_ANGLE, Robot.navigation.getDedRecAngle());
		SmartDashboard.putString(DEAD_REC_AREA, Robot.navigation.getDedRecArea());
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
