package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;

public class AutoCenterToGoal extends Command {
	private static final int CENTER = 320;
	private static final int LIMIT_AREA = 10;
	private static final int OUTER_LIMIT_LEFT = 150;
	private static final int OUTER_LIMIT_RIGHT = 490;
	Camera camera = RobotMap.camera;
	private int state;

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
		System.out.println("execute");
		autoCenter();
	}

	@Override
	protected void initialize() {
		this.state = 0;
		System.out.println("enter autoCenter");
		this.setTimeout(5.0); // Timer for the program.
		autoCenter();
	}

	@Override
	protected void interrupted() {
		System.out.println("interrupted.");
		this.end();
	}

	@Override
	protected boolean isFinished() {
		if (this.state == 1) {
			System.out.println("finished - state equals 1.");
			return true;
		} else if (this.isTimedOut()) {
			System.out.println("is timed out.");
			return true;
		} else {
			return false;
		}
	}

	public double turnRateForAutoCenterToGoal() { // Gets turnRate for
		// AutoCenterToGoal. Is
		// supposed to slow the
		// robot down when
		// approaching the dead zone
		// near CENTER.
		double X = camera.getCOG_X();
		if (X <= OUTER_LIMIT_LEFT) { // Lower limits:
			return 0.6;
		} else if (X >= OUTER_LIMIT_RIGHT) {
			return -0.6;
		} else if (X >= OUTER_LIMIT_LEFT && X < CENTER - LIMIT_AREA) {
			return 0.55;
		} else if (X <= OUTER_LIMIT_RIGHT && X > CENTER + LIMIT_AREA) {
			return -0.55;
		} else {
			return 0;
		}
	}

	public void autoCenter() {
		if (!camera.getFileName().startsWith("Object")) {
			System.out.println("not the object");
			state = 1;
			return;
		}
		double x = camera.getCOG_X();
		if (x <= CENTER - LIMIT_AREA) {
			Robot.drive.driveValues(0, this.turnRateForAutoCenterToGoal());
		} else if (x >= CENTER + LIMIT_AREA) {
			Robot.drive.driveValues(0, this.turnRateForAutoCenterToGoal());
		} else {
			Robot.drive.driveValues(0, 0);
			state = 1; // Correction complete.
		}
	}
}
