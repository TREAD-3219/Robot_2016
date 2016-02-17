package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class MultiToolMover extends Command{

	
	
	Joystick joystick1 = null;

	Joystick joystick;

	double speed = 0.0;
	public MultiToolMover() {
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
		joystick1 = Robot.oi.gameController;
	}
	
	@Override
	protected void execute() {
		speed = joystick1.getY();
		Robot.multiTool.driveArmUpDown(speed);
		
		Robot.multiTool.isSwitchSetHigh();
	}



	@Override
	protected void interrupted() {
		end();
		
	}

	@Override
	protected boolean isFinished() {
		boolean finished = false;


		if (Robot.multiTool.isSwitchSetLow() && speed < 0.0) {

		}
		if (Robot.multiTool.isSwitchSetHigh() && speed > 0.0) {
			finished = true;
		}

		return finished;
	}
	
	@Override
	protected void end() {
		Robot.multiTool.driveArmUpDown(0.0);
	}
}
