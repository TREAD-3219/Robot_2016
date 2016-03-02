package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopShooter extends Command { //used in command groups
	//this command just stops the shooter
	
	public StopShooter() {
		requires(Robot.shooter);
	}
	
	@Override
	protected void initialize() {
		Robot.shooter.spinDown();
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
	}

	@Override
	protected void interrupted() {
		end();
	}

}
