package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.commands.AutoStraightCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EngageRamp extends AutoStraightCommand {

	String whichCommand;

	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		gyroStraight(0.6);
		if ((whichCommand.equalsIgnoreCase("Drawbridge") || whichCommand.equalsIgnoreCase("Chival de Frise"))
				&& !(Robot.sensors.armEncoderAngle() > 0)) {
			Robot.multiTool.driveArmUpDown(0.2);
		}
	}

	@Override
	protected void initialize() {
		whichCommand = SmartDashboard.getString("AutoCommand", "NoSelectedCommand");
		gyroStraight(0.6);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		if (Robot.sensors.getTip() >= 5.0) {
			return true;
		} else
			return false;
	}
}
