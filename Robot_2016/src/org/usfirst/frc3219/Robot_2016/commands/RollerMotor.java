package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RollerMotor extends Command{// Starts roller motor
	// stops either when button b is released, or feed mech limit switch is hit
	//public Joystick rollerSpeedSlider = Robot.oi.joystick; 
	boolean pressed;
	double direction;
	
	public RollerMotor() {

		requires(Robot.multiTool);
		this.direction = 1;

	}
	
	public RollerMotor(double direction) {
		requires(Robot.drive);
		this.direction = direction;
		this.pressed = false;
	}
	

	@Override
	protected void initialize() {
		Robot.multiTool.driveRoller(-1.0 * direction);
		this.setTimeout(5);
		pressed = false;
	}

	@Override
	protected void execute() {
		Robot.multiTool.driveRoller(-1.0 * direction);
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		boolean finished = false;
		if (pressed) {
			finished = !Robot.feedMech.getLimitSwitch();
		}
		
		return finished || this.isTimedOut();
	}

	@Override
	protected void end() {
		Robot.multiTool.driveRoller(0.0);
	}

}
