package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WatchSensors extends Command {

	public WatchSensors() {
		requires(Robot.sensors);
	}
	
	public static void showDataFromSensor() {
		Sensors sensors = Robot.sensors;
		SmartDashboard.putNumber("Lidar1", sensors.readLidar1());
		SmartDashboard.putNumber("Shooter RPM", sensors.readShooterCounter() );
		SmartDashboard.putBoolean(Sensors.LINE_SEEKER_TAG, Robot.sensors.readLineSeeker());
		SmartDashboard.putNumber("Ultra1", sensors.readUltraSonic1());
		SmartDashboard.putNumber("RotationCounter", sensors.readShooterCounter1());
	}
	
	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		showDataFromSensor();
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
