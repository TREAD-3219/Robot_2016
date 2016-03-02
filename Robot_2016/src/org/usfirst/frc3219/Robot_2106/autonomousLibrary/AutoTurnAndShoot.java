package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.Robot.Position;

import edu.wpi.first.wpilibj.command.Scheduler;

public class AutoTurnAndShoot extends AutoStraightCommand {

	String whichPosition;
	double initEncoderDist;
	double currentEncoderDist;

	@Override
	protected void end() {
		Robot.drive.setBrakesOn();
		Robot.drive.driveValues(0.0, 0.0);
	}

	@Override
	protected void execute() {
		currentEncoderDist = Robot.sensors.aveDistEncoders() - initEncoderDist;
		setGyroStraight(0.45, 20);
	}

	@Override
	protected void initialize() {
		this.setTimeout(2.5);
		initEncoderDist = Robot.sensors.aveDistEncoders();
		switch (Robot.position) {
		case A:
			setGyroStraight(0.45, 36.0);
			break;
		case B:
			setGyroStraight(0.45, 20);
			break;
		case C:
			setGyroStraight(0.45, 355);
			break;
		case D:
			setGyroStraight(0.45, 300);
			break;
			
			default:
				System.out.println("Unknown Position in AutoTurnAndShoot: " + Robot.position.name());
				break;
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
