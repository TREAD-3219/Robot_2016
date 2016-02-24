package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Navigation;

import edu.wpi.first.wpilibj.command.Command;

// do we need this command?  Won't the encoders give us speed?
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
		Robot.navigation.setSpeed(speed);
		lastTime = System.currentTimeMillis();
		lastDistance = (RobotMap.driveEncoderLeft.getDistance() + RobotMap.driveEncoderRight.getDistance()) / 2;
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
