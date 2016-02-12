package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.Navigation;
import org.usfirst.frc3219.Robot_2016.subsystems.Sensors;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class DeadReckoningChecks extends Command {
	
	private static double deadRecX = Navigation.getDeadRecX();
	private static double deadRecY = Navigation.getDeadRecY();
	private static double deadRecAngle = Navigation.getDeadRecAngle();

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		double angleIsOffBy = Robot.sensors.getCompass() - Navigation.getDeadRecAngle();
		if (Math.abs(angleIsOffBy) <= 1.0) {
			Navigation.deadRecTurned(angleIsOffBy);
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
