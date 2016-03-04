package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.Navigation;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// A class for Deductive Reckoning navigation
public class DedReckoningChecks extends NeverFinishCommand {

	public DedReckoningChecks() {
		this.setRunWhenDisabled(true);
		requires(Robot.navigation);
	}
	
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
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
