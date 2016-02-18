package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.Navigation;
import org.usfirst.frc3219.Robot_2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WatchSensors extends Command {
	private static Sensors sensors = Robot.sensors;

	public WatchSensors() {
		requires(Robot.sensors);
	}
	
	public static void showDataFromSensor() {
		SmartDashboard.putNumber(Sensors.LIDAR_TAG, sensors.readLidar1());
		SmartDashboard.putBoolean("On Carpet", Robot.sensors.readLineSeeker());
		SmartDashboard.putNumber("Angle", sensors.getAngle());
		SmartDashboard.putString("Location", Navigation.getDeadRecArea());
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
		this.showDataFromSensor();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
