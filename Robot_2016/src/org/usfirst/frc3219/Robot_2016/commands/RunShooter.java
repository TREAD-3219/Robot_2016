package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RunShooter extends Command {
	private double speed;
	
	public RunShooter() {
		requires(Robot.shooter);
		speed = 10.0;
	}
	
	public RunShooter(double wheelSpeed) {
		requires(Robot.shooter);
		speed = wheelSpeed;
	}

	@Override
	protected void initialize() {
		Robot.shooter.shoot(speed);
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
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
