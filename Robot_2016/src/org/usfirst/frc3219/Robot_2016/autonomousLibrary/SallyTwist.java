package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class SallyTwist extends Command {
	double initialDirection;
	double finalDirection;
	double lastMeasurement = 0.0;
	
	private static final double SALLY_TWIST_SPEED = 0.6;

	// SallyPort command 2
	// after this reverse drive the robot through the sally port
	@Override
	protected void initialize() {
		initialDirection = Robot.sensors.navx.getFusedHeading();
		Robot.drive.driveValues(0.0, SALLY_TWIST_SPEED);
		
	}

	@Override
	protected void execute() {
		Robot.drive.driveValues(0.0, SALLY_TWIST_SPEED);
		// SmartDashboard.putNumber("Fused Heading", Robot.ahrs.);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		finalDirection = Robot.sensors.navx.getFusedHeading();
		boolean finished = false;
		if (finalDirection - lastMeasurement > 300) {
			initialDirection += 360;
		}
		
		
		
		if (finalDirection - initialDirection <= -170) {
		 finished = true; 
		}
		lastMeasurement = finalDirection;
		return finished;
	}
		

	@Override
	protected void end() {
		Robot.drive.setBrakesOn();
		Robot.drive.driveValues(0.0, 0.0);
	}
}
