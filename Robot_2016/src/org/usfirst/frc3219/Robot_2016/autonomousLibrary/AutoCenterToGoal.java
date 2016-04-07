package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Camera;
import org.usfirst.frc3219.Robot_2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoCenterToGoal extends Command {
	private static final String NO_CENTERING_POSSIBLE = "NoCenteringPossible";
	private static final String TURN_LEFT_AUTO_CENTER = "TurnLeftAutoCenter";
	private static final String TURN_RIGHT_AUTO_CENTER = "TurnRightAutoCenter";
	public static final String TURN_RATE_FOR_AUTOCENTER = "TurnRateForAutoCenter";
	public static final String OVERLAP_FOR_AUTOCENTER = "OverlapForAutoCenter";
	private static final double TIMEOUT = 5.0;

	private static final int CENTER = 320;
	private static final int LIMIT_AREA = 5;
	private static final int OUTER_LIMIT_LEFT = 150;
	private static final int OUTER_LIMIT_RIGHT = 490;
	// Camera camera = RobotMap.camera;
	private int state = 0;
	private boolean turningLeft = false;
	private boolean turningRight = false;
	// private int centerPoint = CENTER;

	public AutoCenterToGoal() {
		// requires(Robot.drive);
		SmartDashboard.putNumber(Shooter.CENTER_POINT, CENTER);
	}

	@Override
	// Ends stops the turn.
	protected void end() {
		System.out.println("finished AutoCenterToGoal");
		SmartDashboard.putBoolean(Shooter.IS_CENTERED, true);
		Robot.drive.driveValues(0, 0);
		state = 0;
	}

	@Override
	protected void execute() {
		autoCenter();
	}

	@Override
	protected void initialize() {
		state = 0;
		if (Robot.camera.getCOG_X() <= CENTER - LIMIT_AREA) { // TURN LEFT
																// PLEASE!
			turningLeft = true;
		} else if (Robot.camera.getCOG_X() >= CENTER + LIMIT_AREA) {
			turningRight = true;
		} else {
			state = 1;
			return;
		}
		SmartDashboard.putBoolean(TURN_RIGHT_AUTO_CENTER, false);
		SmartDashboard.putBoolean(TURN_LEFT_AUTO_CENTER, false);
		SmartDashboard.putBoolean(NO_CENTERING_POSSIBLE, false);
		SmartDashboard.putBoolean(Shooter.IS_CENTERED, false);
		System.out.println("enter autoCenter");
		this.setTimeout(TIMEOUT); // Timer for the program.
		autoCenter();
		// centerPoint = (int) SmartDashboard.getNumber(Shooter.CENTER_POINT,
		// CENTER);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		// this should also check the drive stick to see if the driver
		// is attempting to regain control.
		return state == 1 || this.isTimedOut();
	}

	public double turnRateAutoCenter() { // Gets turnRate for
		// AutoCenterToGoal. Is supposed to slow the
		// robot down when approaching the dead zone
		// near CENTER.

		// double X = camera.getCOG_X();
		// if (X <= OUTER_LIMIT_LEFT) { // Lower limits:
		// return 0.65;
		// } else if (X >= OUTER_LIMIT_RIGHT) {
		// return -0.65;
		// } else if (X >= OUTER_LIMIT_LEFT && X < CENTER - LIMIT_AREA) {
		// return 0.55;
		// } else if (X <= OUTER_LIMIT_RIGHT && X > CENTER + LIMIT_AREA) {
		// return -0.55;
		// } else
		// return state = 1;

		// TEST
		// ---------------------------------------
		double targetPos_X = Robot.camera.getCOG_X();
		double drivePower = SmartDashboard.getNumber(TURN_RATE_FOR_AUTOCENTER); // 0.65
																				// is
																				// default
																				// power.
		double overlap = SmartDashboard.getNumber(OVERLAP_FOR_AUTOCENTER);
		if (turningLeft) {
			if (targetPos_X <= CENTER + overlap) {
				return drivePower;
			} else {
				// STOP
				state = 1;
				return 0.0;
			}
		} else if (turningRight) {
			if (targetPos_X >= CENTER - overlap) {
				return -drivePower;
			} else {
				// STOP
				state = 1;
				return 0.0;
			}
		} else {
			// STOP
			SmartDashboard.putBoolean(TURN_RIGHT_AUTO_CENTER, false);
			SmartDashboard.putBoolean(TURN_LEFT_AUTO_CENTER, false);
			SmartDashboard.putBoolean(NO_CENTERING_POSSIBLE, true); // If within
																	// null area
			// or outside camera
			// viewing range or
			// lost target.
			state = 1;
			return 0.0;
		}

		// if (targetPos_X <= CENTER - LIMIT_AREA) { // Lower limits:
		// SmartDashboard.putBoolean(TURN_LEFT_AUTO_CENTER, true);
		// return drivePower;
		// } else if (targetPos_X >= CENTER + LIMIT_AREA) {
		// SmartDashboard.putBoolean(TURN_RIGHT_AUTO_CENTER, true);
		// return -drivePower;
		// } else {
		// SmartDashboard.putBoolean(TURN_RIGHT_AUTO_CENTER, false);
		// SmartDashboard.putBoolean(TURN_LEFT_AUTO_CENTER, false);
		// SmartDashboard.putBoolean(NO_CENTERING_POSSIBLE, true); // If within
		// // null area
		// // or outside camera
		// // viewing range or
		// // lost target.
		// state = 1;
		// return 0.0;
		// }
	}

	public void autoCenter() {
		if (!Robot.camera.getFileName().startsWith("Object")) {
			state = 1;
			return;
		} else {
			Robot.drive.driveValues(0, this.turnRateAutoCenter());
		}
		// double cogX = camera.getCOG_X();
		// if (cogX <= centerPoint - LIMIT_AREA || cogX >= centerPoint +
		// LIMIT_AREA) {
		// Robot.drive.driveValues(0, this.turnRateAutoCenter());
		// } else {
		// Robot.drive.driveValues(0, 0);
		// state = 1; // Correction complete.
		// }
	}
}
