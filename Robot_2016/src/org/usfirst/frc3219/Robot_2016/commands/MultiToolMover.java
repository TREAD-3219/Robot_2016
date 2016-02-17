package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class MultiToolMover extends Command{
	Joystick joystick;
	double speed = 0.0;
	public MultiToolMover() {
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
		joystick = Robot.oi.gameController;
		
	}

	@Override
	protected void execute() {
		speed = joystick.getY();
		Robot.multiTool.driveArmUpDown(speed);
	}



	@Override
	protected void interrupted() {
		end();
		
	}

	@Override
	protected boolean isFinished() {
		boolean finished = false;
		if (Robot.multiTool.limitSwitchLow.get() && speed < 0.0) {
			finished = true;
		}
		if (Robot.multiTool.limitSwitchHigh.get() && speed > 0.0) {
			finished = true;
		}
		return finished;
	}
	
	@Override
	protected void end() {
		Robot.multiTool.driveArmUpDown(0.0);
	}
}
