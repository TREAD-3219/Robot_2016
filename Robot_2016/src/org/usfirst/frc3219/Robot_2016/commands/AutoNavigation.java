package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Navigation;

import edu.wpi.first.wpilibj.command.Command;

public class AutoNavigation extends Command {
	double lastLeftEncoder;
	double lastRightEncoder;
	@Override
	protected void initialize() {
		lastLeftEncoder = RobotMap.driveEncoderLeft.getDistance();
		lastRightEncoder = RobotMap.driveEncoderRight.getDistance();
		
	}

	@Override
	protected void execute() {
		double newLeftDist = RobotMap.driveEncoderLeft.getDistance() - lastLeftEncoder;
		double newRightDist = RobotMap.driveEncoderRight.getDistance() - lastRightEncoder;
		double avgDist = (newLeftDist + newRightDist) / 2;
		Navigation.deadRecMoved(avgDist);
		double degrees = 2 * (newLeftDist - newRightDist);
		Navigation.deadRecTurned(degrees);
		lastLeftEncoder = RobotMap.driveEncoderLeft.getDistance();
		lastRightEncoder = RobotMap.driveEncoderRight.getDistance();
		
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
