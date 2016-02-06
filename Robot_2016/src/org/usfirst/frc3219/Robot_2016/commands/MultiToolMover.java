package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class MultiToolMover extends Command{
<<<<<<< HEAD
	
	
	Joystick joystick = null;
=======
	Joystick joystick;
>>>>>>> refs/remotes/origin/master
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
		
		Robot.multiTool.isSwitchSetHigh();
	}



	@Override
	protected void interrupted() {
		end();
		
	}

	@Override
	protected boolean isFinished() {
		boolean finished = false;
<<<<<<< HEAD
		if (Robot.multiTool.isSwitchSetLow() && speed < 0.0) {
=======
		/*if (Robot.multiTool.limitSwitchLow.get() && speed < 0.0) {
>>>>>>> refs/remotes/origin/master
			finished = true;
		}
		if (Robot.multiTool.isSwitchSetLow() && speed > 0.0) {
			finished = true;
<<<<<<< HEAD
		}
		
=======
		}*/
>>>>>>> refs/remotes/origin/master
		return finished;
	}
	
	@Override
	protected void end() {
		Robot.multiTool.driveArmUpDown(0.0);
	}
}
