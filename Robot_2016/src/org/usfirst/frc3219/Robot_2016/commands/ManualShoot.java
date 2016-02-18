package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualShoot extends Command { //This class spins shooter when the trigger is pressed
	double topPower;
	double bottomPower;
	public ManualShoot() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		Robot.shooter.spinUp(0, 0);
		topPower = SmartDashboard.getNumber("Top Wheel Speed");
		bottomPower = SmartDashboard.getNumber("Bottom Wheel Speed");
	}

	@Override
	protected void execute() {
			Robot.shooter.spinUp(topPower, bottomPower);
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
	}

}
