package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc3219.autonomousLibrary.AutoCenterToGoal;

// Counter does not work for this command, only for ManualShoot and RunShooter.
public class AutoShoot extends CommandGroup {
	// This is for the full feed and shooting mechanisms
	
	public AutoShoot() {
		requires(Robot.feedMech);
		requires(Robot.shooter);
		createShooterCommands();
	}
	
	public void createShooterCommands() {
		this.addSequential(new RunShooter(.7));
		this.addParallel(new AutoCenterToGoal());
		this.addSequential(new FeedShooter(.3));
		this.addSequential(new StopShooter());
	}
}