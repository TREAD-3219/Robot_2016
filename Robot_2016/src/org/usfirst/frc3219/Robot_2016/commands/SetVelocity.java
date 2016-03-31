package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetVelocity extends Command {

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		double dist = SmartDashboard.getNumber(Shooter.DEFAULT_SHOOTING_DISTANCE_TAG);
		double v = Robot.shooter.findVelocityForPoint(dist, 80 - 27);
		double power = Robot.shooter.findMotorSpeed(v);
		if (power <= 1.0 && power >= 0.0) {
			SmartDashboard.putNumber(Shooter.BOTTOMSHOOTER, power);
			SmartDashboard.putNumber(Shooter.TOPSHOOTER, power);
		} else if (power > 1.0) {
			SmartDashboard.putNumber(Shooter.BOTTOMSHOOTER, 1.0);
			SmartDashboard.putNumber(Shooter.TOPSHOOTER, 1.0);
		} else if(power < 0.0) {
			SmartDashboard.putNumber(Shooter.BOTTOMSHOOTER, 1.0);
			SmartDashboard.putNumber(Shooter.TOPSHOOTER, .7);
		}
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
}
