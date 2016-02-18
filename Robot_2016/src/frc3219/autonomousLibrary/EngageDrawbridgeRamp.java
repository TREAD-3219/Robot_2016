package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.commands.AutoStraightCommand;

public class EngageDrawbridgeRamp extends AutoStraightCommand {


	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		gyroStraight(0.6);
		if(!(Robot.sensors.armEncoderDistance() < 1)) {
			Robot.multiTool.driveArmUpDown(0.2); // Arbitrary guess!
		}
	}

	@Override
	protected void initialize() {
		gyroStraight(0.6);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		if(Robot.sensors.getTip() >= 5.0) {
			return true;
		} else
		return false;
	}
}
