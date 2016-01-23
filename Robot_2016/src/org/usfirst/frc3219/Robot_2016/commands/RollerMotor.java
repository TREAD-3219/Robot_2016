package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RollerMotor extends Command{

	public RollerMotor() {
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
		Robot.multiTool.driveRollerMotor.set(10.0);
	}
	
	@Override
	protected void execute() {
		
	}

	

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void end() {
		Robot.multiTool.driveRollerMotor.set(0.0);
		
	}
	
}
