package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;
import org.usfirst.frc3219.Robot_2016.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WatchSensors extends NeverFinishCommand {
	private static Sensors sensors = Robot.sensors;

	public WatchSensors() {
		requires(Robot.sensors);
	}

	public void showDataFromSensor() {
		SmartDashboard.putNumber(Sensors.LIDAR_TAG, sensors.readLidar1());
		SmartDashboard.putNumber(Sensors.SHOOTER_RPM_TAG, Math.round(sensors.readShooterCounter()));
		//SmartDashboard.putBoolean(Sensors.LINE_SEEKER_TAG, Robot.sensors.readLineSeeker());
		SmartDashboard.putNumber(Sensors.ROTATION_COUNTER_TAG, sensors.readShooterCounter1());
		double compassAngle = sensors.getAngle();
		SmartDashboard.putNumber(Sensors.COMPASS_TAG, compassAngle);
		SmartDashboard.putNumber("Angle", sensors.getAngle());
		//SmartDashboard.putString("Location", Navigation.getDeadRecArea());
		SmartDashboard.putNumber(MultiTool.ARM_ENCODER_TAG, Robot.sensors.armEncoderAngle());
		SmartDashboard.putNumber("DedRecX", Robot.navigation.getDedRecX());
		SmartDashboard.putNumber("DedRevY", Robot.navigation.getDedRecX());
		SmartDashboard.putNumber("DedRecAngle", Robot.navigation.getDedRecAngle());
	}

	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		this.showDataFromSensor();
	}

	@Override
	protected void initialize() {
		this.showDataFromSensor();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
