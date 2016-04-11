package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.FeedMech;
import org.usfirst.frc3219.Robot_2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualShoot extends Command { // This class spins shooter when the
											// trigger is pressed
	public static final String BOTTOM_WHEEL_SPEED = "ShooterBottom";
	public static final String TOP_WHEEL_SPEED = "ShooterTop";
	double topPower;
	double bottomPower;

	public ManualShoot() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		SmartDashboard.putBoolean(FeedMech.BALL_FED_TAG, false);
		RobotMap.shooterCounter.reset();
		Robot.shooter.spinUp(0, 0);
		topPower = SmartDashboard.getNumber(TOP_WHEEL_SPEED, Shooter.TOP_SHOOTER_SPEED);
		bottomPower = SmartDashboard.getNumber(BOTTOM_WHEEL_SPEED, Shooter.BOTTOM_SHOOTER_SPEED);
		Robot.shooter.spinUp(topPower, bottomPower);
	}

	@Override
	protected void execute() {
		topPower = SmartDashboard.getNumber(TOP_WHEEL_SPEED, Shooter.TOP_SHOOTER_SPEED);
		bottomPower = SmartDashboard.getNumber(BOTTOM_WHEEL_SPEED, Shooter.BOTTOM_SHOOTER_SPEED);
		Robot.shooter.spinUp(topPower, bottomPower);
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.shooter.spinDown();
		Robot.feedMech.stopFeeder();
	}

}
