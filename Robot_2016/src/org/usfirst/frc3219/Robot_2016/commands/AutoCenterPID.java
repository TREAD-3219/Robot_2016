package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class AutoCenterPID extends PIDCommand {
	private static final double CENTER_X = 320.0;
	private static final double P = 1.0 / CENTER_X;
	private static final double I = P / 20.0;
	private static final double D = 0.0;
	
	public AutoCenterPID() {
		super(P, I, D);
	}
	
	@Override
	protected double returnPIDInput() {
		
		double cogX = Robot.camera.getCOG_X();
		double delta = cogX - CENTER_X;
		return delta;
	}

	@Override
	protected void usePIDOutput(double arg0) {
		Robot.drive.driveValues(0, -arg0);
	}

	@Override
	protected void end() {
		Robot.drive.driveValues(0.0, 0.0);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected void initialize() {
		this.getPIDController().setAbsoluteTolerance(10.0);
		this.getPIDController().setContinuous(false);
		this.setSetpoint(CENTER_X);
		this.getPIDController().setInputRange(1.0, 639.0);
		this.getPIDController().setOutputRange(-0.8, 0.8);
		this.getPIDController().enable();
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut() || this.getPIDController().onTarget();
	}

}
