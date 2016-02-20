package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;


public class RollerMotor extends Command{// Starts roller motor
	// stops either when button b is released, or feed mech limit switch is hit
	//public Joystick rollerSpeedSlider = Robot.oi.joystick; 
	boolean pressed = false;
	int direction;
	public RollerMotor() {
		requires(Robot.drive);
		this.direction = 1;
	}
	
	public RollerMotor(int direction) {
		requires(Robot.drive);
		this.direction = direction;
	}
	

	@Override
	protected void initialize() {
		Robot.multiTool.driveRoller(-.5 * direction);
	}

	@Override
	protected void execute() {
		Robot.multiTool.driveRoller(-.6 * direction); // as of 1/30/2016, 12:10 AM the
											// roller motor is broken and needs
											// to be replaced.
		if (!pressed) {
			pressed = Robot.feedMech.getLimitSwitch();
		}

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
		return finished;
	}

	@Override
	protected void end() {
		Robot.multiTool.driveRoller(0.0);

	}

}
