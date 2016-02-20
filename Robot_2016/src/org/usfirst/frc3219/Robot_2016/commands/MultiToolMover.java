package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MultiToolMover extends Command {
	Joystick gameController;
	double speed = 0.0;

	public MultiToolMover() {
		requires(Robot.multiTool);
	}

	@Override
	protected void initialize() {
		gameController = Robot.oi.gameController;
		speed = gameController.getY();
		Robot.multiTool.armSetPoint(MultiTool.STOW_TAG);
	}

	@Override
	protected void execute() {

		speed = gameController.getY() * 0.5;
		Robot.multiTool.driveArmUpDown(speed);
		if(speed > 0.1){
			if((speed < 0.0 && !Robot.multiTool.readLowerMultiToolLimitSwitch()) 
					|| (speed > 0.0 && !Robot.multiTool.readUpperMultiToolLimitSwitch())){
				Robot.multiTool.driveArmUpDown(speed);
			} else {
				Robot.multiTool.stopMotors();
				Robot.multiTool.driveArmHold();
			}
			
		}
		
		

	}

	@Override
	protected void interrupted() {
		end();

	}

	@Override
	protected boolean isFinished() {
		return false;

	}

	@Override
	protected void end() {
		Robot.multiTool.driveArmUpDown(0.0); // stop motors
	}
}
