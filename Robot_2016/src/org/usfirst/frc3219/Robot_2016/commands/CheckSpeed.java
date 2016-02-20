package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Navigation;

import edu.wpi.first.wpilibj.command.Command;

public class CheckSpeed extends Command {
	double lastDistance;
	double lastTime;

	@Override
	protected void initialize() {
		lastTime = System.currentTimeMillis();
		lastDistance = (RobotMap.driveEncoderLeft.getDistance() + RobotMap.driveEncoderRight.getDistance()) / 2;
	}

	@Override
	protected void execute() {
		double newDistance = (RobotMap.driveEncoderLeft.getDistance() + RobotMap.driveEncoderRight.getDistance()) / 2;
		double newTime = System.currentTimeMillis();
		double speed = (newDistance - lastDistance) / (newTime - lastTime);
		Navigation.setSpeed(speed);
		lastTime = System.currentTimeMillis();
		lastDistance = (RobotMap.driveEncoderLeft.getDistance() + RobotMap.driveEncoderRight.getDistance()) / 2;

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
