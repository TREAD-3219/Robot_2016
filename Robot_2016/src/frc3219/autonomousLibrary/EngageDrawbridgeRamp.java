package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.Drive;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;
import org.usfirst.frc3219.Robot_2016.subsystems.Sensors;

public class EngageDrawbridgeRamp extends AutoStraightCommand {


	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		gyroStraight(Drive.MEDIUM_SPEED);
		if(!(Robot.sensors.armEncoderDistance() < MultiTool.LOW_ARM_ANGLE_LIMIT)) {
			Robot.multiTool.driveArmUpDown(MultiTool.LOW_SPEED_DOWN); // Arbitrary guess!
		}
	}

	@Override
	protected void initialize() {
		gyroStraight(Drive.MEDIUM_SPEED);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		if(Robot.sensors.getTip() >= Sensors.HIT_RAMP_JERK_LIMIT) {
			return true;
		} else
		return false;
	}
}
