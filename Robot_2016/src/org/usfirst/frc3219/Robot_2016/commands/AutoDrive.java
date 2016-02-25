package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDrive extends Command {
	double speed;
	double time = 2;
	double distance;
	double encoderDist;
	
	public AutoDrive(double speed, double distance) {
		this.speed = speed;
		this.distance = distance;
	}
	@Override
	protected void initialize() {
		this.setTimeout(time);
		Robot.drive.driveValues(speed, 0);
		encoderDist = Robot.sensors.getAvgEncoderDist();
	}
	
	@Override
	protected void end() {
		Robot.drive.driveValues(0, 0);
		
	}
	@Override
	protected void execute() {
		// if speed were to represent a distance/time and not a
		// power input, you could check if the robot was actually
		// going that speed and increase or decrease power here
		// as appropriate.  low speeds would be much more
		// controllable.
		// I'd also derive this from AutoStraight, so the direction
		// could be corrected as well.
		Robot.drive.driveValues(speed, 0); // hau fst th robt gos
	}
	
	@Override
	protected void interrupted() {
		end();
		
	}
	@Override
	protected boolean isFinished() {
		double avgDistance = Robot.sensors.getAvgEncoderDist();
		boolean encoderHit = avgDistance >= distance;
		if (this.isTimedOut()) {
			SmartDashboard.putString("Wheel stopped because", "Timed out");
		} else if (encoderHit) {
			SmartDashboard.putString("Wheel stopped because", "Encoders Hit");
		} else {
			SmartDashboard.putString("Wheel stopped because", "null");
		}
		return this.isTimedOut() || encoderHit;
	}
}
