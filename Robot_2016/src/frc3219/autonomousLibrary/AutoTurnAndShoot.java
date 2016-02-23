package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class AutoTurnAndShoot extends AutoStraightCommand {

	String whichPosition;
	double initEncoderDist;
	double currentEncoderDist;

	@Override
	protected void end() {
		Robot.drive.driveValues(0.0, 0.0);
	}

	@Override
	protected void execute() {
		currentEncoderDist = Robot.sensors.aveDistEncoders() - initEncoderDist;
		if (whichPosition.equalsIgnoreCase("position a")) { // 36 degrees
			setGyroStraight(0.45, 36.0);
		} else if (whichPosition.equalsIgnoreCase("position b")) {
			setGyroStraight(0.45, 20);
		} else if (whichPosition.equalsIgnoreCase("position c")) {
			setGyroStraight(0.45, 355);
		} else if (whichPosition.equalsIgnoreCase("position d")) {
			setGyroStraight(0.45, 300);
		}
	}

	@Override
	protected void initialize() {
		this.setTimeout(2.5);
		initEncoderDist = Robot.sensors.aveDistEncoders();
		whichPosition = (String) Robot.oi.AutoDefenseChooser.getSelected();
		if (whichPosition.equalsIgnoreCase("position a")) {
			setGyroStraight(0.45, 36.0);
		} else if (whichPosition.equalsIgnoreCase("position b")) {
			setGyroStraight(0.45, 20);
		} else if (whichPosition.equalsIgnoreCase("position c")) {
			setGyroStraight(0.45, 355);
		} else if (whichPosition.equalsIgnoreCase("position d")) {
			setGyroStraight(0.45, 300);
		}
	}

	@Override
	protected void interrupted() {
		this.end();

	}

	@Override
	protected boolean isFinished() {
		if (this.isTimedOut() || currentEncoderDist > 70) {
			return true;
		} else {
			return false;
		}
	}

}
