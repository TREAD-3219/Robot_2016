package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoCenterPID extends PIDCommand {
	public static final String AUTO_CENTER_PID_DRIVE_POWER = "AutoCenterPID drivePower";
	public static final String AUTO_CENTER_PID_STARTED = "AutoCenterPID started";
	private static final double CENTERING_TIMEOUT = 3.0;
	private static final double IMAGE_CENTER_TOLERANCE_PIXELS = 10.0;
	private static final double MOTOR_POWER_MAX = 0.8;
	private static final double IMAGE_RIGHT_X = 639.0;
	private static final double IMAGE_LEFT_X = 0.0;
	private static final double CENTER_X = (IMAGE_LEFT_X + IMAGE_RIGHT_X) / 2;
	private static final double P = 0.6 / CENTER_X; // full output at edge of frame
	private static final double I = 0.0;
			//P / 20.0;
	private static final double D = 0.0;
	
	private double lastDelta = 0.0;
	private boolean error = false;
	private int errorCount = 0;
	private double centerX;
	
	public AutoCenterPID() {
		super(P, I, D);
	}
	
	@Override
	protected double returnPIDInput() {
		// get distance of cogX from center
		double cogX = Robot.camera.getCOG_X();
		// the delta is the error!
		double delta = centerX - cogX;
		if (!Robot.camera.targetDetected()) {
			SmartDashboard.putBoolean(Shooter.TARGET_NOT_VISIBLE, true);
			errorCount += 1;
			if (errorCount > 50) {
				error = true;
			}
			return lastDelta; // GOOD MOVE?
		}
		lastDelta = delta;
		return delta;
	}

	@Override
	protected void usePIDOutput(double driveTurnPower) {
		// should we enforce a minimum?
		SmartDashboard.putNumber(AUTO_CENTER_PID_DRIVE_POWER, driveTurnPower);
		Robot.drive.driveValues(0, -driveTurnPower);
	}

	@Override
	protected void end() {
		Robot.drive.driveValues(0.0, 0.0);
		SmartDashboard.putBoolean(Shooter.IS_CENTERED, true);
		Robot.drive.setBrakesOn();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected void initialize() {
		SmartDashboard.putBoolean(AUTO_CENTER_PID_STARTED, true);
		SmartDashboard.putBoolean(Shooter.IS_CENTERED, false);
		this.error = false;
		this.errorCount = 0;
		SmartDashboard.putBoolean(Shooter.IS_CENTERED, false);
		centerX = SmartDashboard.getNumber(Shooter.CENTER_POINT, CENTER_X);
		if (!Robot.camera.targetDetected()) {
			SmartDashboard.putBoolean(Shooter.TARGET_NOT_VISIBLE, true);
			error = true;
			return;
		} else {
			SmartDashboard.putBoolean(Shooter.TARGET_NOT_VISIBLE, false);
			this.lastDelta = centerX - Robot.camera.getCOG_X();
		}
		
		Robot.drive.setBrakesOff();
		this.setTimeout(CENTERING_TIMEOUT);
		this.getPIDController().setAbsoluteTolerance(IMAGE_CENTER_TOLERANCE_PIXELS);
		this.getPIDController().setContinuous(false);
		this.setSetpoint(centerX);
		this.getPIDController().setInputRange(IMAGE_LEFT_X, IMAGE_RIGHT_X);
		this.getPIDController().setOutputRange(-MOTOR_POWER_MAX, MOTOR_POWER_MAX);
		this.getPIDController().enable();
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		return this.error || this.isTimedOut() || this.getPIDController().onTarget();
	}

}
