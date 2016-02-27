package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoCenterToGoal extends Command {
	
	Camera camera = RobotMap.camera;
	private int state = 0;
	
	private static final double TIMEOUT_SECONDS = 15.0;
	private static final int RUN_STATE = 0;
	private static final int END_STATE = 1;
	
	// These numbers refer to the number of pixels along the x-axis of the
	// camera image
	private static final int CENTER = 320;
	private static final int LIMIT_AREA = 10;
	private static final int OUTER_LIMIT_LEFT = 150;
	private static final int OUTER_LIMIT_RIGHT = 490;

	// For turnRate:
	private static final double SPEED_MAX = 0.75;
	private static final double MINIMUM_SPEED = 0.4;
	private static final double UP_SCALER = 3.5;
	private static final double DOWN_SCALER = 0.003;
	private static final int MATH_POWER = 3;
	private static final int SHIFTED_CENTER = 0;

	public AutoCenterToGoal() {
		requires(Robot.drive);
	}

	@Override
	// Ends stops the turn.
	protected void end() {
		System.out.println("finished");
		SmartDashboard.putBoolean("IsCentered", true);
		Robot.drive.driveValues(0, 0);
		SmartDashboard.putBoolean("IsCentered", true);
		state = RUN_STATE; // So it works the second time!!!
	}

	@Override
	protected void execute() {
		autoCenter();
	}

	@Override
	protected void initialize() {
		SmartDashboard.putBoolean("IsCentered", false);
		System.out.println("enter autoCenter");
		this.setTimeout(TIMEOUT_SECONDS); // Timer for the program.
		autoCenter();
		SmartDashboard.putBoolean("IsCentered", false);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		if (this.state == END_STATE || this.isTimedOut()) {
			return true;
		} else {
			return false;
		}
	}

	public double turnRateAutoCenter() { // Gets turnRate for

		// AutoCenterToGoal. Is
		// supposed to slow the
		// robot down when
		// approaching the dead zone
		// near CENTER.

		double X = camera.getCOG_X();
		// Make 0 the origin.
		X -= CENTER;
		double rate;

		if (X > SHIFTED_CENTER) {
			rate = UP_SCALER * Math.pow((-X * DOWN_SCALER), MATH_POWER) - MINIMUM_SPEED;
		} else {
			rate = UP_SCALER * Math.pow((-X * DOWN_SCALER), MATH_POWER) + MINIMUM_SPEED;
		}

		if (rate > SPEED_MAX) {
			rate = SPEED_MAX;
		} else if (rate < -SPEED_MAX) {
			rate = -SPEED_MAX;
		}

		return rate;

		// Old code, currently works so DO NOT DELETE

		/*
		 * double X = camera.getCOG_X(); if (X <= OUTER_LIMIT_LEFT) { // Lower
		 * limits: return 0.45; } else if (X >= OUTER_LIMIT_RIGHT) { return
		 * -0.45; } else if (X >= OUTER_LIMIT_LEFT && X < CENTER - LIMIT_AREA) {
		 * return 0.35; } else if (X <= OUTER_LIMIT_RIGHT && X > CENTER +
		 * LIMIT_AREA) { return -0.35; } else return 0; }
		 */
	}

	public void autoCenter() {
		if (!camera.getFileName().startsWith("Object")) {
			state = END_STATE;
			return;
		}
		double x = camera.getCOG_X();
		if (x <= CENTER - LIMIT_AREA || x >= CENTER + LIMIT_AREA) {
			Robot.drive.driveValues(0, this.turnRateAutoCenter());
		} else {
			Robot.drive.driveValues(0, 0);
			state = END_STATE; // Correction complete.
		}
	}
}
