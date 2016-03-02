package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShootBoulder extends Command {
	private static final double FEEDER_RUN_SPEED = .3;
	private static final double FEEDER_STOP_SPEED = 0.0;
	private static final double BOULDER_SHOT_TIME = .3;
	private static final double SHOOTER_SPINUP_TIME = 0.7; // long enough?

	enum ShootStates {
		start, spinup, feed, stop;
	}

	ShootStates states = ShootStates.start;
	double topPower;
	double bottomPower;
	double feederSpeed;
	double startTime;
	boolean finished;

	public ShootBoulder() {
		requires(Robot.shooter);
	}
	
	@Override
	protected void initialize() {
		topPower = SmartDashboard.getNumber(Shooter.TOPSHOOTER, 0.0);
		bottomPower = SmartDashboard.getNumber(Shooter.BOTTOMSHOOTER, 0.0);
		feederSpeed = FEEDER_STOP_SPEED;
		Robot.shooter.spinUp(topPower, bottomPower);
		states = ShootStates.spinup;
		startTime = Timer.getFPGATimestamp();
	}

	@Override
	protected void execute() {
		Robot.shooter.spinUp(topPower, bottomPower);
		Robot.feedMech.spinFeeder(feederSpeed);
		double deltaTime = Timer.getFPGATimestamp() - startTime;
		
		switch (states) {
		case spinup:
			if ((Robot.shooter.atSpeed()
					&& SmartDashboard.getBoolean("IsCentered"))
					|| deltaTime > SHOOTER_SPINUP_TIME) {
				states = ShootStates.feed;
				feederSpeed = FEEDER_RUN_SPEED;
				startTime = Timer.getFPGATimestamp();
			}
			break;
		case feed:
			if (deltaTime > BOULDER_SHOT_TIME) {
				states = ShootStates.stop;
			}
			break;
		case stop:
			feederSpeed = FEEDER_STOP_SPEED;
			finished = true;
			
		default:
			System.out.println("ShootBoulder unknown state: " + states.name());
			finished = true;
			break;
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
