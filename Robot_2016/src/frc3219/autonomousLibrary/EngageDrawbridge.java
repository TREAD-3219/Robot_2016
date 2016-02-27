package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.Drive;

public class EngageDrawbridge extends AutoStraightCommand {
	double initAccelX;
	double initAccelY;
	private static final double JERK_THRESHOLD = 0.5; // Not calibrated.
	
	@Override
	protected void end() {
		Robot.drive.setBrakesOn();
	}

	@Override
	protected void execute() {
		gyroStraight(Drive.LOW_SPEED);

	}

	@Override
	protected void initialize() {
		gyroStraight(Drive.LOW_SPEED);

	}

	@Override
	protected void interrupted() {
		end();

	}

	@Override
	protected boolean isFinished() {
		double finAccelX = Robot.sensors.navx.getWorldLinearAccelX();
		double finAccelY = Robot.sensors.navx.getWorldLinearAccelY();
		double xJerk = Math.abs(finAccelX - initAccelX);
		double yJerk = Math.abs(finAccelY - initAccelY);
		if(xJerk > JERK_THRESHOLD || yJerk > JERK_THRESHOLD) {
			return true;
		} else {
			return false;
		}
	}

}
