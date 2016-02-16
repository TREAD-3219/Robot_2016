package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;

public class AutoCenterToGoal extends Command {
	private static final int CENTER = 320;
	private static final int LIMIT_AREA = 10;
	//private static final double UPPER_POWER_LIMIT = 0.7;
	//private static final double LOWER_POWER_LIMIT = 0.3;
	private static final int OUTER_LIMIT_LEFT = 150;
	private static final int OUTER_LIMIT_RIGHT = 490;
	Camera camera = RobotMap.camera;
	// private double x = 0;
	private int state = 0;
	// Is not calibrated for distance!

	public AutoCenterToGoal() {
		requires(Robot.drive);
	}

	@Override
	// Ends stops the turn.
	protected void end() {
		Robot.drive.driveValues(0, 0);
		state = 0; // So it works the second time!!!
	}

	@Override
	protected void execute() {
		autoCenter();
	}

	@Override
	protected void initialize() {
		System.out.println("enter autoCenter");
		this.setTimeout(15.0); // Timer for the program.
		autoCenter();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		if (this.state == 1 || this.isTimedOut()) {
			System.out.println("finished");
			return true;
		} else {
			return false;
		}
	}

	public double turnRate() { // Gets turnRate for
		// AutoCenterToGoal. Is
		// supposed to slow the
		// robot down when
		// approaching the dead zone
		// near CENTER.
		// Y = -0.00390625 X + 1.25
		//double slope = -0.00285714285; // -1/350
		double X = camera.getCOG_X();
		// double turnRate = slope * (X - CENTER); // -0.8
		//double turnRate = 0.45;
		if (X <= OUTER_LIMIT_LEFT) { // Lower limits:
			return 0.45;
		} else if (X >= OUTER_LIMIT_RIGHT) {
			return -0.45;
		} else

		// if (turnRate < LOWER_POWER_LIMIT && turnRate > 0.0) { // Lower
		// limits:
		// return LOWER_POWER_LIMIT;
		// } else if (turnRate > -LOWER_POWER_LIMIT && turnRate < 0.0) {
		// return -LOWER_POWER_LIMIT;
		// } else if (turnRate > UPPER_POWER_LIMIT) { // Upper limits:
		// return UPPER_POWER_LIMIT;
		// } else if (turnRate < -UPPER_POWER_LIMIT) {
		// return -UPPER_POWER_LIMIT;
		// } else {
		// return turnRate;
		// }
		if (X >= OUTER_LIMIT_LEFT && X < CENTER - LIMIT_AREA) {
			return 0.35;
		} else if (X <= OUTER_LIMIT_RIGHT && X > CENTER + LIMIT_AREA) {
			return -0.35;
		} else
			return 0;

	}

	public void autoCenter() {
		if (!camera.getFileName().startsWith("Object")) {
			state = 1;
			return;
		}
		double x = camera.getCOG_X();
		if (x <= CENTER - LIMIT_AREA) {
			Robot.drive.driveValues(0, this.turnRate());
			// Robot.drive.driveValues(0, 0.39);
		} else if (x >= CENTER + LIMIT_AREA) {
			Robot.drive.driveValues(0, this.turnRate());
			// Robot.drive.driveValues(0, -0.39);
		} else {
			Robot.drive.driveValues(0, 0);
			state = 1;// Correction complete.
		}
	}

	// Was execute():

	// if (!camera.getFileName().startsWith("Object")) {
	// this.state = 1;
	// } else if (camera.getCOG_X() >= CENTER + LIMIT_AREA) { // Target to the
	// right.
	// //Robot.drive.driveSpeed(0, this.turnRateForAutoCenterToGoal()); //
	// Turning
	// // left.
	// Robot.drive.driveValues(0, this.turnRateForAutoCenterToGoal()); // Twitch
	// the Robot!
	// } else if (camera.getCOG_X() <= CENTER - LIMIT_AREA) { // Target to the
	// left.
	// //Robot.drive.driveSpeed(0, this.turnRateForAutoCenterToGoal()); //
	// Turning
	// // right.
	// Robot.drive.driveValues(0, this.turnRateForAutoCenterToGoal());
	// } else {
	// this.state = 1;
	// }

}
