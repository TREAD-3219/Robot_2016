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
	private static final double TIMEOUT = 4.0;

	private static final int CENTER = 320;
	private static final int LIMIT_AREA = 5;
	private static final int OUTER_LIMIT_LEFT = 150;
	private static final int OUTER_LIMIT_RIGHT = 490;
	// Camera camera = RobotMap.camera;
	private static final int CONTINUE = 0;
	private static final int END = 1;
	private int state = CONTINUE;
	private boolean turningLeft = false;
	private boolean turningRight = false;
	// private int centerPoint = CENTER;
	private int droppedFramesCount = 0;
	private double lastTurnRate = 0;

	public AutoCenterToGoal() {
		SmartDashboard.putNumber(Shooter.CENTER_POINT, CENTER);
		SmartDashboard.putBoolean(Shooter.IS_CENTERED, false);
	}

	@Override
	// Ends stops the turn.
	protected void end() {
		System.out.println("finished AutoCenterToGoal");
		SmartDashboard.putBoolean(Shooter.IS_CENTERED, true);
		Robot.drive.driveValues(0, 0);
		// RESET FIELDS:
		state = CONTINUE;
		turningLeft = false;
		turningRight = false;
		droppedFramesCount = 0;
		lastTurnRate = 0.0;
		// -------------------
		Robot.drive.setBrakesOn();
	}

	@Override
	protected void execute() {
		autoCenter();
	}

	@Override
	protected void initialize() {
		state = CONTINUE;
		droppedFramesCount = 0;
		lastTurnRate = 0.0;
		Robot.drive.setBrakesOff();
		if (Robot.camera.getCOG_X() <= CENTER - LIMIT_AREA) { // TURN LEFT
																// PLEASE!
			turningLeft = true;
			turningRight = false;
		} else if (Robot.camera.getCOG_X() >= CENTER + LIMIT_AREA) {
			turningRight = true;
			turningLeft = false;
		} else {
			state = END;
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
		return state == END || this.isTimedOut();
	}

	public double turnRateAutoCenter() { // Gets turnRate for
		// AutoCenterToGoal. Is supposed to slow the
		// robot down when approaching the dead zone
		// near CENTER.

		// TEST
		// ---------------------------------------
		double targetPos_X = Robot.camera.getCOG_X();
		double drivePower = SmartDashboard.getNumber(TURN_RATE_FOR_AUTOCENTER); // 0.65
																				// is
																				// default
																				// power.
		if (!Robot.camera.targetDetected()) {
			droppedFramesCount++;
			if (droppedFramesCount > 15) {

				state = END;
				return 0.0;
			}
			return lastTurnRate;
		}

		double overlap = SmartDashboard.getNumber(OVERLAP_FOR_AUTOCENTER);
		if (turningLeft) {
			if (targetPos_X <= CENTER + overlap) {
				lastTurnRate = drivePower;
				droppedFramesCount = 0;
				return drivePower;
			} else {
				// STOP
				state = END;
				return 0.0;
			}
		} else if (turningRight) {
			if (targetPos_X >= CENTER - overlap) {
				lastTurnRate = -drivePower;
				droppedFramesCount = 0;
				return -drivePower;
			} else {
				// STOP
				state = END;
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
			state = END;
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
			state = END;
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
