package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopRobotDrive extends Command {

	public static final String STOP_ROBOT_DRIVE_FINISH_TAG = "StopRobotDriveFinish";

	public StopRobotDrive() {
		super("StopRobotDrive");
	}
	
	@Override
	protected void initialize() {
		Robot.drive.setBrakesOn();
		Robot.drive.driveValues(0.0, 0.0);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		Robot.drive.setBrakesOn();
		Robot.drive.driveValues(0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
