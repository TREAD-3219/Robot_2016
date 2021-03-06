package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EngageRamp extends AutoStraightCommand {
	public static final String IS_TIPPED_TAG = "isTipped";
	public static final String DROP_ARMS_START_TAG = "DropArmsStart";
	public static final String DROP_ARMS_FINISH_TAG = "DropArmsFinish";
	public static final String ENGAGE_RAMP_FINISH_TAG = "EngageRampFinish";

	private static final double MIN_TIP_ANGLE = 8.0; // Used to be 6.5
	private static final double RAMP_SPEED = 0.8;

	double dropTime;
	private double armSpeed;
	private double initialTime = 0;

	@Override
	protected void end() {
		Robot.multiTool.stopMotors();
		SmartDashboard.putBoolean(ENGAGE_RAMP_FINISH_TAG, true);
	}

	@Override
	protected void execute() {
		super.gyroStraight(RAMP_SPEED);
	}

	@Override
	protected void initialize() {
		SmartDashboard.putBoolean(IS_TIPPED_TAG, Robot.sensors.getTip() >= MIN_TIP_ANGLE);
		this.setTimeout(2.0);
		initialTime = Timer.getFPGATimestamp();
		SmartDashboard.putBoolean(ENGAGE_RAMP_FINISH_TAG, false);
		SmartDashboard.putBoolean(DROP_ARMS_START_TAG, true);
		//this.dropTime = Timer.getFPGATimestamp();
		Robot.sensors.navx.reset();
		Robot.drive.setBrakesOff();
		super.gyroStraight(RAMP_SPEED);
		armSpeed = 0;
		
		switch (Robot.defense) {
		case ChevalDeFrise:
			//Robot.multiTool.armSetPoint(MultiTool.CHEVAL_DE_FRISE_START);
			armSpeed = 0;
			break;
			
		case Drawbridge:
			//Robot.multiTool.armSetPoint(MultiTool.DRAWBRIDGE_START);
			armSpeed = 0;
			break;
			
		default:
			armSpeed = 0.7;
			break;
		} 
		
		//Robot.multiTool.driveArmUpDown(armSpeed);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		SmartDashboard.putBoolean(IS_TIPPED_TAG, Robot.sensors.getTip() >= MIN_TIP_ANGLE);
		SmartDashboard.putBoolean("Engage Ramp Timed", this.isTimedOut());
		//SmartDashboard.putNumber("isTippedDegree", Robot.sensors.getTip());
		boolean tipped = Timer.getFPGATimestamp() - initialTime >= 1.0 && Robot.sensors.getTip() >= MIN_TIP_ANGLE;
		return tipped || this.isTimedOut();
	}
}
