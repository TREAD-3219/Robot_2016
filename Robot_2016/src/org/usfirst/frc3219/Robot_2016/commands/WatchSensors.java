package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.Drive;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;
import org.usfirst.frc3219.Robot_2016.subsystems.Navigation;
import org.usfirst.frc3219.Robot_2016.subsystems.Sensors;
import org.usfirst.frc3219.Robot_2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WatchSensors extends NeverFinishCommand {
	private static Sensors sensors = Robot.sensors;

	public WatchSensors() {
		this.setRunWhenDisabled(true);
		this.setInterruptible(false);
		requires(Robot.sensors);
	}

	public void showDataFromSensor() {
		SmartDashboard.putNumber(Sensors.LIDAR_TAG, sensors.readLidar1());
		//SmartDashboard.putNumber(MultiTool.ARM_ENCODER_TAG, Robot.sensors.armEncoderAngle());
		//SmartDashboard.putNumber(Sensors.LEFT_ENCODER_TAG, Robot.sensors.leftEncoderDistance());
		//SmartDashboard.putNumber(Sensors.RIGHT_ENCODER_TAG, Robot.sensors.rightEncoderDistance());
		//SmartDashboard.putNumber(Drive.DISTANCE_TAG, Robot.drive.getAvgEncoderDist());
		SmartDashboard.putBoolean("IsCentered", false);
		SmartDashboard.putBoolean("isTipped", Robot.sensors.getTip() >= 8);
		SmartDashboard.putNumber("isTippedDegree", Robot.sensors.getTip());
		SmartDashboard.putNumber("AutoRotateAngle", Robot.sensors.navx.getAngle() - 360);
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
