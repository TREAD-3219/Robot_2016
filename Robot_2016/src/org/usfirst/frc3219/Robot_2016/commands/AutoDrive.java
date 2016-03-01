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
		encoderDist = (RobotMap.driveEncoderLeft.getDistance() + RobotMap.driveEncoderRight.getDistance()) / 2;
		
	}
	@Override
	protected void end() {
		Robot.drive.driveValues(0, 0);
		
	}
	@Override
	protected void execute() {
		Robot.drive.driveValues(speed, 0); // hau fst th robt gos
		
	}
	@Override
	protected void interrupted() {
		end();
		
	}
	@Override
	protected boolean isFinished() {
		boolean encoderHit = false;//((RobotMap.driveEncoderLeft.getDistance() + RobotMap.driveEncoderRight.getDistance()) / 2) - encoderDist >= distance;
		return this.isTimedOut() || encoderHit;
	}
}
