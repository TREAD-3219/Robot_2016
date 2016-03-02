package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoCenterToGoal extends Command {
	public static final String IS_CENTERED = "IsCentered";
	public static final String CENTER_POINT = "Center Point";
	
	private static final int CENTER = 320;
	private static final int LIMIT_AREA = 10;
	private static final int OUTER_LIMIT_LEFT = 150;
	private static final int OUTER_LIMIT_RIGHT = 490;
	private static final int LIMIT_LEFT_ADJUST = OUTER_LIMIT_LEFT - CENTER;
	private static final int LIMIT_RIGHT_ADJUST = OUTER_LIMIT_RIGHT - CENTER;
	Camera camera = RobotMap.camera;
	private int state = 0;
	private int centerPoint = CENTER;
	private int limitLeft = OUTER_LIMIT_LEFT;
	private int limitRight = OUTER_LIMIT_RIGHT;

	public AutoCenterToGoal() {
		requires(Robot.drive);
		SmartDashboard.putNumber(CENTER_POINT, CENTER);
	}

	@Override
	// Ends stops the turn.
	protected void end() {
		System.out.println("finished");
		SmartDashboard.putBoolean(IS_CENTERED, true);
		Robot.drive.driveValues(0, 0);
		SmartDashboard.putBoolean(IS_CENTERED, true);
		state = 0; // So it works the second time!!!
	}

	@Override
	protected void execute() {
		autoCenter();
	}

	@Override
	protected void initialize() {
		SmartDashboard.putBoolean(IS_CENTERED, false);
		System.out.println("enter autoCenter");
		this.setTimeout(15.0); // Timer for the program.
		autoCenter();
		centerPoint = (int) SmartDashboard.getNumber(CENTER_POINT, CENTER);
		limitLeft = centerPoint + LIMIT_LEFT_ADJUST;
		limitRight = centerPoint + LIMIT_RIGHT_ADJUST;
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		return this.state == 1 || this.isTimedOut();
	}
	
	public double turnRateAutoCenter() { // Gets turnRate for
		// AutoCenterToGoal. Is
		// supposed to slow the
		// robot down when
		// approaching the dead zone
		// near CENTER.
		
		double cogX = camera.getCOG_X();
		//Make 0 the origin.
		cogX -= centerPoint;
		double rate;
		
		if(cogX > 0) {
			rate = 3.5 * Math.pow((-cogX*0.003), 3) - 0.4;
		} else {
			rate = 3.5 * Math.pow((-cogX*0.003), 3) + 0.4;
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
		} else
			return 0;
		}*/
	}

	public void autoCenter() {
		if (!camera.getFileName().startsWith("Object")) {
			state = 1;
			return;
		}
		
		double cogX = camera.getCOG_X();
		if (cogX <= centerPoint - LIMIT_AREA || cogX >= centerPoint + LIMIT_AREA) {
			Robot.drive.driveValues(0, this.turnRateAutoCenter());
		} else {
			Robot.drive.driveValues(0, 0);
			state = 1; // Correction complete.
		}
	}}
