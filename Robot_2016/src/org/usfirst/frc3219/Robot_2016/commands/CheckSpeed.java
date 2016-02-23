package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Navigation;

import edu.wpi.first.wpilibj.command.Command;

public class CheckSpeed extends Command {
	double lastDistance;
	double lastTime;

	@Override
	protected void initialize() {
		lastTime = System.currentTimeMillis();
		lastDistance = Robot.sensors.getAvgEncoderDist();
	}

	@Override
	protected void execute() {
		double newDistance = Robot.sensors.getAvgEncoderDist();
		double newTime = System.currentTimeMillis();
		double speed = (newDistance - lastDistance) / (newTime - lastTime);
		Navigation.setSpeed(speed);
		lastTime = System.currentTimeMillis();
		lastDistance = newDistance;
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
