package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import edu.wpi.first.wpilibj.command.Command;

public abstract class AutoStraightCommand extends Command {
	public void gyroStraight(double speed) {
		double angle = Robot.sensors.navx.getAngle() + 180;
		double Kp = 0.03;
		Robot.drive.driveValues(speed, -angle * Kp);
	}
}
