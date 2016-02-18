package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SallyTwist extends Command {
	double initialDirection;
	double finalDirection;
	double lastMeasurement = 0.0;

	// SallyPort command 2
	// after this reverse drive the robot through the sally port
	@Override
	protected void initialize() {
		initialDirection = Robot.ahrs.getFusedHeading();
		Robot.drive.driveValues(0.0, 0.6);
		
	}

	@Override
	protected void execute() {
		Robot.drive.driveValues(0.0, 0.6);
		// SmartDashboard.putNumber("Fused Heading", Robot.ahrs.);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		finalDirection = Robot.ahrs.getFusedHeading();
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
		RobotMap.driveLeftDrive.enableBrakeMode(true);
		RobotMap.driveRightDrive.enableBrakeMode(true);
		Robot.drive.driveValues(0.0, 0.0);
	}
}
