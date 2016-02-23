package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DrawbridgeExecuteArms extends Command {

	@Override
	protected void end() {
		Robot.multiTool.driveArmUpDown(0.0);

	}

	@Override
	protected void execute() {
		Robot.multiTool.driveArmUpDown(-0.1);

	}

	@Override
	protected void initialize() {
		Robot.multiTool.driveArmUpDown(-0.1);

	}

	@Override
	protected void interrupted() {
		this.end();

	}

	@Override
	protected boolean isFinished() {
		if (Robot.sensors.armEncoderAngle() >= 95) {
			return true;
		} else {
			return false;
		}
	}

}
