package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Camera;
import org.usfirst.frc3219.Robot_2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoCenterToGoal extends Command {
	private static final double TIMEOUT = 5.0;
	private static final double MINIMUM_SPEED = 0.4;
	private static final double UP_SCALER = 3.5;
	private static final double DOWN_SCALER = 0.003;
	private static final double MAX_SPEED = 0.75;

	private static final int CENTER = 320;
	private static final int LIMIT_AREA = 15;
	private static final int OUTER_LIMIT_LEFT = 150;
	private static final int OUTER_LIMIT_RIGHT = 490;
	//Camera camera = RobotMap.camera;
	private int state = 0;
	private int centerPoint = CENTER;

	public AutoCenterToGoal() {
		//requires(Robot.drive);
		SmartDashboard.putNumber(Shooter.CENTER_POINT, CENTER);
		SmartDashboard.putBoolean(Shooter.IS_CENTERED, false);
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
		SmartDashboard.putBoolean(Shooter.IS_CENTERED, false);
		System.out.println("enter autoCenter");
		this.setTimeout(TIMEOUT); // Timer for the program.
		autoCenter();
		centerPoint = (int) SmartDashboard.getNumber(Shooter.CENTER_POINT, CENTER);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		// this should also check the drive stick to see if the driver
		// is attempting to regain control.
		return this.state == 1 || this.isTimedOut();
	}

	public double turnRateAutoCenter() { // Gets turnRate for
		// AutoCenterToGoal. Is supposed to slow the
		// robot down when approaching the dead zone
		// near CENTER.

		// Old code, do not delete

		/*
		 * double cogX = camera.getCOG_X(); //Make 0 the origin. cogX -=
		 * centerPoint; double rate;
		 * 
		 * if(cogX > 0) { rate = UP_SCALER * Math.pow((-cogX*DOWN_SCALER), 3) -
		 * MINIMUM_SPEED; } else { rate = UP_SCALER *
		 * Math.pow((-cogX*DOWN_SCALER), 3) + MINIMUM_SPEED; }
		 * 
		 * if(rate > MAX_SPEED) { rate = MAX_SPEED; } else if(rate < -MAX_SPEED)
		 * { rate = -MAX_SPEED; }
		 * 
		 * return rate;
		 */

//		double X = camera.getCOG_X();
//		if (X <= OUTER_LIMIT_LEFT) { // Lower limits:
//			return 0.65;
//		} else if (X >= OUTER_LIMIT_RIGHT) {
//			return -0.65;
//		} else if (X >= OUTER_LIMIT_LEFT && X < CENTER - LIMIT_AREA) {
//			return 0.55;
//		} else if (X <= OUTER_LIMIT_RIGHT && X > CENTER + LIMIT_AREA) {
//			return -0.55;
//		} else
//			return state = 1;
		double X = Robot.camera.getCOG_X();
		if (X <= CENTER - LIMIT_AREA) { // Lower limits:
			return 0.65;
		} else if (X >= CENTER + LIMIT_AREA) {
			return -0.65;
		} else //if (X >= OUTER_LIMIT_LEFT && X < CENTER - LIMIT_AREA) {
			//return 0.55;
		//} else if (X <= OUTER_LIMIT_RIGHT && X > CENTER + LIMIT_AREA) {
		//	return -0.55;
		//} else
			return state = 1;
	}

	public void autoCenter() {
		if (!Robot.camera.getFileName().startsWith("Object")) {
			state = 1;
			return;
		}
		
		Robot.drive.driveValues(0, this.turnRateAutoCenter());

//		double cogX = camera.getCOG_X();
//		if (cogX <= centerPoint - LIMIT_AREA || cogX >= centerPoint + LIMIT_AREA) {
//			Robot.drive.driveValues(0, this.turnRateAutoCenter());
//		} else {
//			Robot.drive.driveValues(0, 0);
//			state = 1; // Correction complete.
//		}
	}
}
