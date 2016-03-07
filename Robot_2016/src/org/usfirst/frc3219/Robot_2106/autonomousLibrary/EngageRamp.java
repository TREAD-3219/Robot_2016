package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EngageRamp extends AutoStraightCommand {

	String whichArmPreset;
	private static final double RAMP_SPEED = 0.9;
	double dropTime;

	@Override
	protected void end() {
		//Robot.multiTool.stopMotors();
	}

	@Override
	protected void execute() {
		super.gyroStraight(RAMP_SPEED);
		double deltaT = Timer.getFPGATimestamp() - this.dropTime;
		if (deltaT > 0.6) {
			Robot.multiTool.driveArmUpDown(0.0);
			Robot.multiTool.stopMotors();
			
		}
	}

	@Override
	protected void initialize() {
		//SmartDashboard.putBoolean("isTipped", Robot.sensors.getTip() >= 5);
		this.setTimeout(3.0);
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
			
		case Moat:
			armSpeed = 0.7;
			break;

		case RockWall:
			armSpeed = 0.7;
			break;
			
		default:
			//Robot.multiTool.armSetPoint(MultiTool.STOW);
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
		return Robot.sensors.getTip() >= 5.0 || this.isTimedOut();
		
	}
}
