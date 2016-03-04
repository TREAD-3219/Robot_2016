package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MultiToolMover extends NeverFinishCommand {
	private static final double SPEED_TOLERENCE = 0.1;
	private static final double ARM_STOP_SPEED = 0.0;
	Joystick gameController;
	double speed = 0.0;

	public MultiToolMover() {
		requires(Robot.multiTool);
	}

	@Override
	protected void initialize() {
		gameController = Robot.oi.gameController;
		speed = gameController.getY();
		//Robot.multiTool.armSetPoint(MultiTool.STOW_TAG);
		Robot.multiTool.disable();
	}

	@Override
	protected void execute() {

		// consider a better scaling algorithm here... cubic?
		// also, perhaps asymmetric?  i.e., bigger up than down.
		speed = gameController.getY() * 0.5;


		if(speed > SPEED_TOLERENCE){
			if((speed < 0.0 && !Robot.multiTool.getLowerLimitSwitch()) 
					|| (speed > 0.0 && !Robot.multiTool.getUpperLimitSwitch())){
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
	protected void end() {
		Robot.multiTool.driveArmUpDown(ARM_STOP_SPEED); // stop motors
	}
}
