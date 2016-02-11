package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunShooter extends Command {
	double topPower;
	double bottomPower;
	
	public RunShooter() {
	}
	
	@Override
	protected void initialize() {
		topPower = SmartDashboard.getNumber(Shooter.TOPSHOOTER, 0.0);
		bottomPower = SmartDashboard.getNumber(Shooter.BOTTOMSHOOTER, 0.0);
		Robot.shooter.spinUp(topPower, bottomPower);
		
	}

	@Override
	protected void execute() {
		Robot.shooter.spinUp(topPower, bottomPower);
		
	}
	
	@Override
	protected void interrupted() {
		end();
		
	}

	@Override
	protected boolean isFinished() {
		return !Robot.oi.driveStick.getTrigger();
	}
	
	@Override
	protected void end() {
		Robot.shooter.spinDown();
		
	}
	
}
