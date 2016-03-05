package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class IntakeBall extends Command {
	private static final int TIMEOUT = 5;
// Starts feeder motor
	private static final double ROLLER_SPEED = 1.0;
	private static final double FEEDER_SPEED = .3;
	// stops either when button b is released, or limit switch is hit
	boolean pressed;
	double feederSpeed = FEEDER_SPEED;
	double rollerSpeed = ROLLER_SPEED;
	
	public IntakeBall() {
		requires(Robot.feedMech);
		//requires(Robot.multiTool);
		this.feederSpeed = FEEDER_SPEED;
		this.rollerSpeed = ROLLER_SPEED;
	}

	public IntakeBall(int direction) {
		requires(Robot.feedMech);
		this.feederSpeed = FEEDER_SPEED * direction;
		this.rollerSpeed = ROLLER_SPEED * direction;
	}

	@Override
	protected void initialize() {
		Robot.feedMech.spinFeeder(feederSpeed);
		Robot.multiTool.driveRoller(rollerSpeed);
		this.setTimeout(TIMEOUT);
		pressed = false;
	}

	@Override
	protected void execute() {
		Robot.multiTool.driveRoller(rollerSpeed);
		Robot.feedMech.spinFeeder(feederSpeed);
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
