package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualShoot extends Command { //This class spins shooter when the trigger is pressed
	public static final String BOTTOM_WHEEL_SPEED = "ShooterBottom";
	public static final String TOP_WHEEL_SPEED = "ShooterTop";
	double topPower;
	double bottomPower;
	public ManualShoot() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		RobotMap.shooterCounter.reset();
		Robot.shooter.spinUp(0, 0);
		topPower = SmartDashboard.getNumber(TOP_WHEEL_SPEED, 0.7);
		bottomPower = SmartDashboard.getNumber(BOTTOM_WHEEL_SPEED, 1.0);
		Robot.shooter.spinUp(topPower, bottomPower);
	}

	@Override
	protected void execute() {
				}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		Robot.shooter.spinDown();
		Robot.feedMech.stopFeeder();
	}

}
