package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EngageRamp extends AutoStraightCommand {

	String whichArmPreset;
	private static final double RAMP_SPEED = 0.9;
	double dropTime;

	@Override
	protected void end() {
		//Robot.multiTool.stopMotors();
		SmartDashboard.putBoolean("EngageRampFinished", true);
	}

	@Override
	protected void execute() {
		super.gyroStraight(RAMP_SPEED);
		double deltaT = Timer.getFPGATimestamp() - this.dropTime;
		if (deltaT > 0.6) {
			Robot.multiTool.driveArmUpDown(0.0);
		}
	}

	@Override
	protected void initialize() {
		//SmartDashboard.putBoolean("isTipped", Robot.sensors.getTip() >= 5);
		this.setTimeout(1.5);
		SmartDashboard.putBoolean("EngageRampFinished", false);
		this.dropTime = Timer.getFPGATimestamp();
		Robot.sensors.navx.reset();
		Robot.drive.setBrakesOff();
		super.gyroStraight(RAMP_SPEED);
		double armSpeed = 0;
		
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
		
		Robot.multiTool.driveArmUpDown(armSpeed);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
	//	SmartDashboard.putBoolean("isTipped", Robot.sensors.getTip() >= 5);
		//SmartDashboard.putNumber("isTippedDegree", Robot.sensors.getTip());
		return Robot.sensors.getTip() >= 6.5 || this.isTimedOut();
		
	}
}
