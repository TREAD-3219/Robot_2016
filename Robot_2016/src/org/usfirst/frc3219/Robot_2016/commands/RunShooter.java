package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import org.usfirst.frc3219.Robot_2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunShooter extends Command { //This command is to be used in Command groups
	//This only spins up the shooter DOES NOT SPIN DOWN SHOOTER!
	double topPower;
	double bottomPower;
	double totalTime;
	public RunShooter() {
		requires(Robot.shooter);
		totalTime = 1.0;
	}
	
	public RunShooter(double time) {
		requires(Robot.shooter);
		totalTime = time;
	}

	@Override
	protected void initialize() {
		RobotMap.shooterCounter.reset();
		topPower = SmartDashboard.getNumber(Shooter.TOPSHOOTER, 1.0);
		bottomPower = SmartDashboard.getNumber(Shooter.BOTTOMSHOOTER, 1.0);
		Robot.shooter.spinUp(topPower, bottomPower);
		this.setTimeout(totalTime);
	}

	@Override
	protected void execute() {
		Robot.shooter.spinUp(topPower, bottomPower);
		RobotMap.time = this.timeSinceInitialized();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

	@Override
	protected void end() {
		Robot.shooter.spinDown();

	}
}
