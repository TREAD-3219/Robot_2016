package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/* This class is used to reverse the "front" of the robot.
 * when executed, the Drive will go in the opposite directions
 */

public class ReverseCommand extends Command{

	@Override
	protected void initialize() {
		Robot.drive.reverse();
	}

	@Override
	protected void execute() {
	}

	
	@Override
	protected void interrupted() {

	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}
}
