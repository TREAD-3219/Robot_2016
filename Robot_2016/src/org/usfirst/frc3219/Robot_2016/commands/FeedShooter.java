package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FeedShooter extends Command {
	
	public FeedShooter() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		Robot.shooter.shooterFeeder.set(10.0);
		
	}

	@Override
	protected void execute() {
		
	}


	@Override
	protected void interrupted() {
		end();
		
	}

	@Override
	protected boolean isFinished() {
			
		return false;
	}
	
	@Override
	protected void end() {
		Robot.shooter.shooterFeeder.set(0.0);
		
	}

}