package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickDrive extends Command {

	Joystick driveStick = null;
	
	public JoystickDrive() {
		requires(Robot.drive);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		driveStick = Robot.oi.joystick;
	}
	
	

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double rawFwd = driveStick.getY();
		double rawTurn = driveStick.getX();
		
		Robot.drive.driveValues(rawFwd, rawTurn);
		SmartDashboard.putNumber("Forward", rawFwd);
		SmartDashboard.putNumber("Turn", rawTurn);
		
	}

	

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
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
}
