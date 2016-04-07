package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetVelocity extends Command {
	
	private static final int FULL_MOTOR_SPEED_DISTANCE = 80;
	final int GOAL_HEIGHT = 80;
	final int CAMERA_HEIGHT = 50; //TODO THIS IS A COMPLETE GUESS PLEASE FIX
	final int SHOOTER_HEIGHT = 27;

	@Override
	protected void initialize() {
		//SmartDashboard.putNumber(Shooter.DEFAULT_SHOOTING_DISTANCE_TAG, 100);
		this.setTimeout(1.0);
		
	}

	@Override
	protected void execute() {
		//double pixelHeight = Robot.sensors.getTargetPixls() 
		//double dist = 1/PixelHeight
		//TODO dist would be where you change to fit measurement tests(change number on top, no addition or subtraction)
		//double horizDist = Math.sqrt((dist * dist) - (GOAL_HEIGHT - CAMERA_HEIGHT))
		double dist = SmartDashboard.getNumber(Shooter.DEFAULT_SHOOTING_DISTANCE_TAG);
		if (Robot.sensors.lidarReadingOK()) {
			dist = Robot.sensors.readLidar1();
			if(dist <= FULL_MOTOR_SPEED_DISTANCE) {
				SmartDashboard.putNumber(Shooter.BOTTOMSHOOTER, 1.0);
				SmartDashboard.putNumber(Shooter.TOPSHOOTER, 1.0);
			}
		}
		
		 //TODO THIS IS WHERE YOU CAN CHANGE NUMBERS TO AFFECT SPEED
		double v = Robot.shooter.findVelocityForPoint(dist, GOAL_HEIGHT - SHOOTER_HEIGHT);
		double power = Robot.shooter.findMotorSpeed(v);
		SmartDashboard.putNumber("Shooter Power Theoretical", power);
		if (power <= 1.0 && power >= 0.0) {
			SmartDashboard.putNumber(Shooter.BOTTOMSHOOTER, power);
			SmartDashboard.putNumber(Shooter.TOPSHOOTER, power);
		} else if (power > 1.0) {
			SmartDashboard.putNumber(Shooter.BOTTOMSHOOTER, 1.0);
			SmartDashboard.putNumber(Shooter.TOPSHOOTER, 1.0);
		} else if(power < 0.0) {
			SmartDashboard.putNumber(Shooter.BOTTOMSHOOTER, 0.0);
			SmartDashboard.putNumber(Shooter.TOPSHOOTER, 0.0);
		}
		
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
	
}
