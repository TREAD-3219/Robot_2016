package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TimedForward extends Command {

	@Override
	protected void initialize() {
		this.setTimeout(1.5);
		Robot.drive.driveValues(0.6, 0);

	}

	@Override
	protected void execute() {
		Robot.drive.driveValues(0.6, 0);
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

	@Override
	protected void end() {
		Robot.drive.driveValues(0, 0);

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
