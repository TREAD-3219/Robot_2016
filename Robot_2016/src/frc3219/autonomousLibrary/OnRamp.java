package frc3219.autonomousLibrary;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3219.Robot;
public class OnRamp extends Command {

	String state = "";

	@Override
	protected void end() {
		Robot.drive.driveValues(0, 0);

	}

	@Override
	protected void execute() {
		double sumD = Math.abs(Robot.ahrs.getRawGyroX() + Robot.ahrs.getRawGyroY());
		if(sumD >= 5 || sumD <= -5) {
			state = "farEnough";
		}
		if(state.equalsIgnoreCase("notFarEnough")) {
			Robot.drive.driveValues(0.45, 0);
		} else if(sumD >= 5 || sumD <= -5) {
			Robot.drive.driveValues(1.0, 0.0);
		} else {
			state = "overRamp";
		}
	}

	@Override
	protected void initialize() {
		double sumD = Math.abs(Robot.ahrs.getRawGyroX() + Robot.ahrs.getRawGyroY());
		// May need timeout.
		if(sumD <= 3 && sumD >= -3) {
			Robot.drive.driveValues(0.45, 0);
			state = "notFarEnough";
		} else {
			state = "farEnough";
		}
	}

	@Override
	protected void interrupted() {
		this.end();

	}

	@Override
	protected boolean isFinished() {
		double sumD = Math.abs(Robot.ahrs.getRawGyroX() + Robot.ahrs.getRawGyroY());
		if(state.equalsIgnoreCase("overRamp") && sumD <= 3 && sumD >= -3) {
			return true;
		}
		return false;
	}

}
