package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EngageRamp extends AutoStraightCommand {

	String whichArmPreset;
	private static final double RAMP_SPEED = 0.8;

	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		super.gyroStraight(RAMP_SPEED);
	}

	@Override
	protected void initialize() {
		//SmartDashboard.putBoolean("isTipped", Robot.sensors.getTip() >= 5);
		this.setTimeout(3.0);
		Robot.sensors.navx.reset();
		Robot.drive.setBrakesOff();
		super.gyroStraight(RAMP_SPEED);
		
		switch (Robot.defense) {
		case ChevalDeFrise:
			Robot.multiTool.armSetPoint(MultiTool.CHEVAL_DE_FRISE_START);
			break;
			
		case Drawbridge:
			Robot.multiTool.armSetPoint(MultiTool.DRAWBRIDGE_START);
			break;
			
		default:
			Robot.multiTool.armSetPoint(MultiTool.STOW);
			break;
		}
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
	//	SmartDashboard.putBoolean("isTipped", Robot.sensors.getTip() >= 5);
		//SmartDashboard.putNumber("isTippedDegree", Robot.sensors.getTip());
		return Robot.sensors.getTip() >= 5.0 || this.isTimedOut();
		
	}
}
