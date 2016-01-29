package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualShoot extends Command {
	
	public ManualShoot() {
		requires(Robot.shooter);
	}
	
	
	@Override
	protected void initialize() {
		Robot.shooter.shoot(0.0);
		
	}
	
	@Override
	protected void execute() {
		Robot.shooter.shoot(Robot.oi.gameController.getThrottle());
		
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void end() {
		Robot.shooter.shoot(0.0);
		
	}
	
}
