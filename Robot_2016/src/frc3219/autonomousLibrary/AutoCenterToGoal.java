package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoCenterToGoal extends Command {
	private static final int CENTER = 320;
	private static final int LIMIT_AREA = 10;
	private static final int OUTER_LIMIT_LEFT = 150;
	private static final int OUTER_LIMIT_RIGHT = 490;
	Camera camera = RobotMap.camera;
	private int state = 0;

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
		state = 0; // So it works the second time!!!
	}

	@Override
	protected void execute() {
		autoCenter();
	}

	@Override
	protected void initialize() {
		SmartDashboard.putBoolean("IsCentered", false);
		System.out.println("enter autoCenter");
		this.setTimeout(5.0); // Timer for the program.
		autoCenter();
		SmartDashboard.putBoolean("IsCentered", false);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		if (this.state == 1 || this.isTimedOut()) {
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
		//Make 0 the origin.
		X -= 320;
		double rate;
		
		if(X > 0) {
			rate = 3.5 * Math.pow((-X*0.003), 3) - 0.4;
		} else {
			rate = 3.5 * Math.pow((-X*0.003), 3) + 0.4;
		}
		
		if(rate > 0.75) {
			rate = 0.75;
		} else if(rate < -0.75) {
			rate = -0.75;
		}
		
		return rate;

		// Old code, currently works so DO NOT DELETE
		
		/*double X = camera.getCOG_X();
		if (X <= OUTER_LIMIT_LEFT) { // Lower limits:
			return 0.45;
		} else if (X >= OUTER_LIMIT_RIGHT) {
			return -0.45;
		} else if (X >= OUTER_LIMIT_LEFT && X < CENTER - LIMIT_AREA) {
			return 0.35;
		} else if (X <= OUTER_LIMIT_RIGHT && X > CENTER + LIMIT_AREA) {
			return -0.35;
		} else {
			return 0;
		}*/
	}

	public void autoCenter() {
		if (!camera.getFileName().startsWith("Object")) {
			state = 1;
			return;
		}
		double x = camera.getCOG_X();
		if (x <= CENTER - LIMIT_AREA || x >= CENTER + LIMIT_AREA) {
			Robot.drive.driveValues(0, this.turnRateAutoCenter());
		} else {
			Robot.drive.driveValues(0, 0);
			state = 1; // Correction complete.
		}
	}
}
