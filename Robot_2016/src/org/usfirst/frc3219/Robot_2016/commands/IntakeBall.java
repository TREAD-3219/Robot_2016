package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeBall extends Command { // Starts feeder motor
	// stops either when button b is released, or limit switch is hit
	boolean pressed;
	int direction;
	public IntakeBall() {
		requires(Robot.feedMech);
		requires(Robot.multiTool);
		this.direction = 1;
	}

	public IntakeBall(int direction) {
		requires(Robot.feedMech);
		this.direction = direction;
	}

	@Override
	protected void initialize() {
		Robot.feedMech.spinFeeder(direction, .3);
		Robot.multiTool.driveRoller(-1.0 * direction);
		this.setTimeout(5);
		pressed = false;
	}

	@Override
	protected void execute() {
		Robot.multiTool.driveRoller(-1.0 * direction);
		Robot.feedMech.spinFeeder(direction, .3);
		if (!pressed) {
			pressed = Robot.feedMech.getLimitSwitch();
		}
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
		Robot.feedMech.stopFeeder();
		Robot.multiTool.driveRoller(0.0);
	}

	@Override
	protected void interrupted() {
		end();

	}

}
