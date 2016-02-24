package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShootBoulder extends Command {
	private static final double BOULDER_SHOT_TIME = .3;
	private static final double SHOOTER_SPINUP_TIME = 0.7;

	enum ShootStates {
		start, spinup, feed, stop;
	}

	ShootStates states = ShootStates.start;
	double topPower;
	double bottomPower;
	double feederSpeed;
	double startTime;
	boolean finished;

	@Override
	protected void initialize() {
		topPower = SmartDashboard.getNumber(Shooter.TOPSHOOTER, 0.0);
		bottomPower = SmartDashboard.getNumber(Shooter.BOTTOMSHOOTER, 0.0);
		feederSpeed = 0.0;
		Robot.shooter.spinUp(topPower, bottomPower);
		states = ShootStates.spinup;
		startTime = Timer.getFPGATimestamp();

	}

	@Override
	protected void execute() {
		Robot.shooter.spinUp(topPower, bottomPower);
		Robot.feedMech.spinFeeder(1, feederSpeed);
		double deltaTime = Timer.getFPGATimestamp() - startTime;
		SmartDashboard.putNumber("FPGA TIMESTAMP", Timer.getFPGATimestamp());
		
		switch (states) {
		case spinup:
			if (Robot.shooter.atSpeed() && SmartDashboard.getBoolean("IsCentered")) {
				states = ShootStates.feed;
				feederSpeed = .3;
				startTime = Timer.getFPGATimestamp();
			}
			if (deltaTime > SHOOTER_SPINUP_TIME) {
				states = ShootStates.feed;
				feederSpeed = .3;
				startTime = Timer.getFPGATimestamp();
			}
			break;
		case feed:
			if (deltaTime > BOULDER_SHOT_TIME) {
				states = ShootStates.stop;
			}
			break;
		case stop:
			feederSpeed = 0.0;
			finished = true;
		}
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {
		Robot.feedMech.stopFeeder();
		Robot.shooter.spinDown();

	}

	@Override
	protected void interrupted() {
		end();

	}

}
