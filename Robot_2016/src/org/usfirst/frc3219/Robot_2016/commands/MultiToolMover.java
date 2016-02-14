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
		double encoder = Robot.multiTool.multiToolEncoder.getDistance();
		if (Robot.multiTool.selectedTool == 0) {
			if (encoder <= 1 || encoder >= 75) {
				finished = true;
			}
		} else if (Robot.multiTool.selectedTool == 1) { //THESE STILL NEED TO BE FILLED IN
			if (encoder <= 1 || encoder >= 75) { //THESE VALUES STILL NEED TO BE SET CORRECTLY
				finished = true;
			}
		} else if (Robot.multiTool.selectedTool == 2) {
			if (encoder <= 1 || encoder >= 75) {
				finished = true;
			}
		} else if (Robot.multiTool.selectedTool == 3) {
			if (encoder <= 1 || encoder >= 75) {
				finished = true;
			}
		}
		return finished;
	}
	
	@Override
	protected void end() {
		Robot.multiTool.driveArmUpDown(0.0);
	}
}
