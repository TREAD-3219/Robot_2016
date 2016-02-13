package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class RollerMotor extends Command{// Starts roller motor
	// stops either when button b is released, or feed mech limit switch is hit
	//public Joystick rollerSpeedSlider = Robot.oi.joystick; 
	public RollerMotor() {
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
		Robot.multiTool.driveRoller(10.0);
	}
	
	@Override
	protected void execute() {
		Robot.multiTool.driveRoller(3.0); // as of 1/30/2016, 12:10 AM the roller motor is broken and needs to be replaced.
		
	}
	
	

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return Robot.feedMech.getLimitSwitch();
	}
	
	@Override
	protected void end() {
		Robot.multiTool.driveRoller(0.0);
		
	}
	
}
