package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MultiToolMover extends Command {
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
		if (Robot.multiTool.selectedTool == 0) {
			SmartDashboard.putString("Selected Tool", "0");
			if (joystick.getTrigger()) {
				//AutoCommand for obstacle
			}
			
		} else if (Robot.multiTool.selectedTool == 1) {
			SmartDashboard.putString("Selected Tool", "1");
			
		} else if (Robot.multiTool.selectedTool == 2) {
			SmartDashboard.putString("Selected Tool", "2");
			
		} else if (Robot.multiTool.selectedTool == 3) {
			SmartDashboard.putString("Selected Tool", "3");
			
		} else if (Robot.multiTool.selectedTool == 4) {
			SmartDashboard.putString("Selected Tool", "4");
		}
	}

	@Override
	protected void interrupted() {
		end();

	}

	@Override
	protected boolean isFinished() {
		boolean finished = false;
		double encoder = Robot.multiTool.multiToolEncoder.getDistance();
			if (encoder <= 1 || encoder >= 75) { //THESE VALUES STILL NEED TO BE SET CORRECTLY
				finished = true;
			}
		
		return finished;
	}

	@Override
	protected void end() {
		Robot.multiTool.driveArmUpDown(0.0);
	}
}
