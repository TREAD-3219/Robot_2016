package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoCenterToGoal extends Command {
	private static final double TIMEOUT = 5.0;
	private static final double MINIMUM_SPEED = 0.4;
	private static final double UP_SCALER = 3.5;
	private static final double DOWN_SCALER = 0.003;
	private static final double MAX_SPEED = 0.75;
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
		this.setTimeout(TIMEOUT); // Timer for the program.
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
		X -= CENTER;
		double rate;
		
		if(X > 0) {
			rate = UP_SCALER * Math.pow((-X*DOWN_SCALER), 3) - MINIMUM_SPEED;
		} else {
			rate = UP_SCALER * Math.pow((-X*DOWN_SCALER), 3) + MINIMUM_SPEED;
		}
		
		if(rate > MAX_SPEED) {
			rate = MAX_SPEED;
		} else if(rate < -MAX_SPEED) {
			rate = -MAX_SPEED;
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
		} else
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
	}}
