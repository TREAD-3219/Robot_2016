package org.usfirst.frc3219.Robot_2016.commands;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3219.Robot_2016.*;
import org.usfirst.frc3219.Robot_2016.subsystems.Drive;

public class AutoDrive extends Command {
	public static final String AUTO_ROTATE_RATE = "AutoRotateRate";
	public static final String AUTO_SPEED = "AutoSpeed";
	public static final String AUTO_DISTANCE = "AutoDistance";

	private static final String AUTO_TIMEOUT = "Auto Timeout";
	private static final String CURRENT_SPEED_SETTING = "Current Speed Setting";
	private static final String CURRENT_TURN_SETTING = "Current Turn Setting";
	private static final String RECORDED_SPEED = "Recorded Speed";
	private static final String AUTO_DRIVE_RUNNING = "AutoDrive Running";

	private static final double THETA_THRESHHOLD = 0.05;
	private static final double SPEED_THRESHHOLD = 0.5;
	private double distance;
	private double speed;
	private double rotateRate;
	private double currentSpeedSetting;
	private double currentTurnSetting;
	private double finalDistance;
	private long startTime;
	private double lastCheck;
	private int speedCounts = 0;
	private double speedSum = 0.0;

	public AutoDrive(double d) {
		requires(Robot.drive);
		distance = d;
		speed = Drive.MAX_SPEED_IPS / 3.0;
		rotateRate = 0.0;
		lastCheck = Double.MAX_VALUE;
		System.out.println("AutoDrive(" + d + ")");
	}

	public AutoDrive(double d, double s) {
		requires(Robot.drive);
		distance = d;
		speed = s;
		rotateRate = 0.0;
		lastCheck = Double.MAX_VALUE;
		System.out.println("AutoDrive(" + d + ", " + s + ")");
	}

	public AutoDrive(double d, double s, double r) {
		requires(Robot.drive);
		distance = d;
		speed = s;
		rotateRate = r;
		lastCheck = Double.MAX_VALUE;
		System.out.println("AutoDrive(" + d + ", " + s + ", " + r + ")");
	}

	@Override
	protected void initialize() {
		SmartDashboard.putString("AutoCommand", "AutoDrive");
		SmartDashboard.putBoolean(AUTO_DRIVE_RUNNING, true);
		currentSpeedSetting = speed;
		currentTurnSetting = rotateRate;
		startTime = Utility.getFPGATime();

		double timeout = Math.abs(distance / speed) + (1.0 / Math.abs(Robot.drive.powerFromSpeed(speed)));
		//double startDistance = Robot.sensors.getDistance();
		//finalDistance = startDistance + distance * Math.signum(speed);
		SmartDashboard.putNumber(AUTO_TIMEOUT, timeout);
		System.out.println(String.format("AutoDrive.initialize: speed(%4.2f), rotate(%4.2f), timeout(%4.2f)", speed,
				rotateRate, timeout));
		Robot.drive.setSafety(false);
		this.setTimeout(timeout);
		Robot.drive.driveSpeed(speed, rotateRate);
	}

	@Override
	protected void execute() {
		Robot.drive.driveSpeed(speed, rotateRate);
	}

	@Override
	protected boolean isFinished() {
		//double distance = Robot.sensors.getDistance();
		double checkDistance = Math.abs(distance - finalDistance);
		boolean result = this.isTimedOut() || checkDistance < 1.0 || checkDistance > lastCheck;
		lastCheck = checkDistance;
		System.out.println(String.format("AutoDrive.isFinished(%s), distance(%4.2f), checkDistance(%4.2f)", result,
				distance, checkDistance));
		return result;
	}

	@Override
	protected void end() {
		System.out.println("AutoDrive.end");
		Robot.drive.setSafety(true);
		Robot.drive.driveValues(0, 0);
		SmartDashboard.putBoolean(AUTO_DRIVE_RUNNING, false);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
