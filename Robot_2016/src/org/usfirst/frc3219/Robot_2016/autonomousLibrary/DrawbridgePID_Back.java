package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class DrawbridgePID_Back extends PIDCommand {
	


	double encoderInit;
	double encoderDiff;
	public static final double MIN_ENCODER_DISTANCE = 60;
	public static final double P = 0.1;
	public static final double I = 0.001;
	public static final double D = 0.0;

	public DrawbridgePID_Back() {
		super(P, I, D);
	}


	@Override
	protected double returnPIDInput() {
		double angle = Robot.sensors.armEncoderAngle();
		double innerRobotAngle = 90 - angle;
		encoderDiff = Robot.drive.getAvgEncoderDist() - encoderInit;
		double angleDrawbridge = Math.asin((Math.sin(innerRobotAngle) * 26) / 37);
		//double arcDrawbridge = (angleDrawbridge * (3.14159 / 180)) * 37;
		//double driveComponentOne = 58.1196 - arcDrawbridge;
		//double arcArms = (innerRobotAngle * (3.14159 / 180) * 26);
		//double driveComponentTwo = 90 - arcArms;
		
		// Second Way: driveError = ramp distance 1 + ramp distance 2
		// Instead of driveError = arc + arc
		double driveComponentTwo_V2 = Math.cos(angleDrawbridge) * 37;
		double driveComponentOne_V2 = Math.cos(innerRobotAngle) * 26;
		
		double driveError_V2 = (driveComponentTwo_V2 + driveComponentOne_V2) - encoderDiff;
		
		//double driveError = driveComponentOne + driveComponentTwo;
		return driveError_V2;
	}

	@Override
	protected void usePIDOutput(double arg0) {
		Robot.drive.driveValues(arg0, 0.0);

	}

	@Override
	protected void end() {
		Robot.drive.setBrakesOn();
		this.getPIDController().disable();
	}

	@Override
	protected void execute() {

	}

	@Override
	protected void initialize() {
		this.getPIDController().enable();
		this.setSetpoint(0.0);
		Robot.drive.driveValues(-0.35, 0.0); // May need to be on drive, not arms.
		Robot.multiTool.getPIDController().setPID(P, I, D);;
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		if(encoderDiff >= 60) { // 63 = distance across the length of arms and drawbridge (perfect distance!)
			return true;
		} else {
		return false;
		}
	}
}
