package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoCenterToGoal;

import edu.wpi.first.wpilibj.command.CommandGroup;

// Counter does not work for this command, only for ManualShoot and RunShooter.
public class AutoShoot extends CommandGroup {
	// This is for the full feed and shooting mechanisms
	
	public AutoShoot() {
		createShooterCommands();
	}
	
	public void createShooterCommands() {
		//this.addSequential(new SetMultiToolPoint(85.0));
		this.addParallel(new ShootBoulder());
		this.addParallel(new AutoCenterToGoal());
	}
}